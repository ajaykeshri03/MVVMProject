package com.ajay.ajaymvvmassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {
    val firstName = MutableLiveData<String>()

    fun sendFirstName(text: String) {
        firstName.value = text
    }

    val lastName = MutableLiveData<String>()

    fun sendLastName(text: String) {
        lastName.value = text
    }

    val age = MutableLiveData<Int>()

    fun sendAge(text: Int) {
        age.value = text
    }
}