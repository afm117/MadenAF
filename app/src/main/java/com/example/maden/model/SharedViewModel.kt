package com.example.maden.model

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class SharedViewModel : ViewModel() {

    private val rUsername = "admin"
    private val rPassword = "ulim"

    var timerStatus = false

    private val _lastSecond = MutableLiveData<Int>()
    val lastSecond: LiveData<Int> = _lastSecond

    private val _lastMinus = MutableLiveData<Int>()
    val lastMinus: LiveData<Int> = _lastMinus

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus



    fun checkLoginStatus(username:String,pass:String){
        _loginStatus.value = username.trim() == rUsername && pass.trim() == rPassword
    }

    fun startTimer(){
        timerStatus = true
        viewModelScope.launch {
            while (timerStatus){

                if ((_lastSecond.value ?: 0) < 59){
                    _lastSecond.value = (_lastSecond.value?:0) + 1
                }else{
                    _lastMinus.value = (_lastMinus.value?:0)+1
                    _lastSecond.value = 0
                }
                delay(1000)

            }
        }
    }

}