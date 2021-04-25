package com.ajay.ajaymvvmassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajay.ajaymvvmassignment.R
import com.ajay.ajaymvvmassignment.viewmodel.ShareViewModel

class MessageReceiverFragment : Fragment() {
    lateinit var firstName :TextView
    lateinit var lastName :TextView
    lateinit var age :TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstName=view.findViewById(R.id.tvFirstName)
        lastName=view.findViewById(R.id.tvLastName)
        age=view.findViewById(R.id.tvAge)
        val model = ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
        model.firstName.observe(viewLifecycleOwner, Observer {

            firstName.text = it
        })
        model.lastName.observe(viewLifecycleOwner, Observer {
           lastName.text=it
        })
        model.age.observe(viewLifecycleOwner, Observer {
            age.text= it.toString()
        })
    }
}