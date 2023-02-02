package com.troy.actionbutton.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

open class BaseViewModel: ViewModel() {

    val error: MutableLiveData<String> = MutableLiveData()

    var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun Job?.cancelIfActive() {
        if (this?.isActive == true) {
            cancel()
        }
    }
}