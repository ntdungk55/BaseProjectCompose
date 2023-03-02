package com.example.basecomposeproject.presentation.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class BaseViewModelSample2 : ViewModel() {

    val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val errorMessage = MutableLiveData<String>()

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "exceptionHandler: $throwable ")
    }

    fun launch(action: suspend () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            action.invoke()
        }
    }

    fun showLoadingDialog(isShowing: Boolean) {
        if (_isLoading.value && !isShowing) {
            viewModelScope.cancel()
        }
        _isLoading.update {
            isShowing
        }
    }

    companion object {
        const val TAG = "BaseViewModel"
    }
}