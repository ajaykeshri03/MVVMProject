package com.ajay.ajaymvvmassignment.utils

import android.R
import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.DialogInterface
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import java.util.*

class DataPicker(context: Context?) : AlertDialog(context), DialogInterface.OnClickListener,
    OnDateChangedListener {
    private var mDateSetListener: OnDateSetListener? = null
    private val mDatePicker: DatePicker? = null

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            BUTTON_POSITIVE -> if (mDateSetListener != null) {
                // Clearing focus forces the dialog to commit any pending
                // changes, e.g. typed text in a NumberPicker.
                mDatePicker?.clearFocus()
                if (mDateSetListener != null) {
                    mDatePicker?.getYear()?.let {
                        mDateSetListener!!.onDateSet(
                            mDatePicker, it,
                            mDatePicker?.getMonth() + 1, mDatePicker.getDayOfMonth()
                        )
                    }
                }
            }
            BUTTON_NEGATIVE -> cancel()
        }
    }

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mDatePicker?.init(year, monthOfYear, dayOfMonth, this)
    }

        fun showDatePicker(listener: OnDateSetListener, defaultDate: Calendar?) {
            var defaultDate = defaultDate
            setButton(BUTTON_POSITIVE, context.getString(R.string.ok), this)
            setButton(BUTTON_NEGATIVE, context.getString(R.string.cancel), this)
            mDateSetListener = listener
            if (defaultDate == null) {
                defaultDate = Calendar.getInstance()
            }
            val year = defaultDate!![Calendar.YEAR]
            val monthOfYear = defaultDate[Calendar.MONTH]
            val dayOfMonth = defaultDate[Calendar.DAY_OF_MONTH]
            mDatePicker!!.init(year, monthOfYear, dayOfMonth, this)
            show()
        }

}