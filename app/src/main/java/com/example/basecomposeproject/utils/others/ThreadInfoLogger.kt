package com.example.basecomposeproject.utils.others

import timber.log.Timber


object ThreadInfoLogger {
    fun logThreadInfo(message: String) {
        Timber.d(message)
    }
}