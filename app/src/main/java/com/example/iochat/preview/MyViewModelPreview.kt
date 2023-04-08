package com.example.iochat.preview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class IMyViewModel : ViewModel(){
    abstract val dynamicValue: StateFlow<String>
    abstract fun getTextA() : String
}

class MyViewModel : IMyViewModel() {
    private val _dynamicValue: MutableStateFlow<String> = MutableStateFlow("")
    override val dynamicValue: StateFlow<String> = _dynamicValue
    override fun getTextA(): String {
        return "HI"
    }


}

class MyViewModelPreview(override val dynamicValue: StateFlow<String> =    MutableStateFlow("no data")) : IMyViewModel() {
    override fun getTextA(): String {
        return ""
    }
}