package com.ajay.ajaymvvmassignment.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ajay.ajaymvvmassignment.R
import com.ajay.ajaymvvmassignment.viewmodel.ShareViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class MessageSenderFragment : Fragment() {
    lateinit var model: ShareViewModel
    lateinit var button:Button
    private var firstName: TextInputEditText? = null
    private var lastName: TextInputEditText? = null
    private var age: TextInputEditText? = null
    lateinit var datePicker:Button
    var  setYear :Int = 0
    var  setMonth:Int = 0
    var  setDayOfMonth :Int = 0
    var totalAge :Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message_sender, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button =view.findViewById(R.id.btNext)
        datePicker=view.findViewById(R.id.btDatePicker)
        firstName = view.findViewById(R.id.tvFirstNameEnter)
        lastName = view.findViewById(R.id.tvLastNameEnter)
        age = view.findViewById(R.id.tvAgeEnter)
        datePicker.setOnClickListener{
            val dialog = context?.let { it1 -> MyDatePickerDialog(it1) }
            dialog?.setTitle("Set Date")
            dialog?.showDatePicker({ view, year, month, dayOfMonth ->
                this.setYear = year
                this.setMonth = month
                this.setDayOfMonth = dayOfMonth
                age?.setText("Year :" + setYear + "  Month :" + setMonth + "  day :" + setDayOfMonth)
                 totalAge = getTotalAgeInYears(year, month, dayOfMonth)
                println("Date selected " + "yy :" + year + "  Month :" + month + "   day :" + dayOfMonth  + "Total years : "   + totalAge)
            }, Calendar.getInstance())
        }

        model = ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
        button.setOnClickListener {
            model.sendFirstName(firstName?.getText().toString())
            model.sendLastName(lastName?.getText().toString())
            model.sendAge(totalAge)

        }
    }

    private fun getTotalAgeInYears(year: Int, month: Int, date: Int) :Int {

        val dobCalendar = Calendar.getInstance()

        dobCalendar[Calendar.YEAR] = year
        dobCalendar[Calendar.MONTH] = month
        dobCalendar[Calendar.DATE] = date

        var ageInteger = 0

        val today = Calendar.getInstance()

        ageInteger = today[Calendar.YEAR] - dobCalendar[Calendar.YEAR]

        if (today[Calendar.MONTH] === dobCalendar[Calendar.MONTH]) {
            if (today[Calendar.DAY_OF_MONTH] < dobCalendar[Calendar.DAY_OF_MONTH]) {
                ageInteger = ageInteger - 1
            }
        } else if (today[Calendar.MONTH] < dobCalendar[Calendar.MONTH]) {
            ageInteger = ageInteger - 1
        }

        return ageInteger
    }
}