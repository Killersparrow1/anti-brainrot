package com.brainrot.android.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
abstract class BaseViewModel @Inject constructor() : ViewModel() {

    protected val scope = viewModelScope

    protected fun <T> launch(block: suspend CoroutineScope.() -> T) {
        scope.launch { block() }
    }
}