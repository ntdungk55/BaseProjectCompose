package com.example.basecomposeproject

import android.app.Application
import android.util.Log

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
    }

    companion object {
        val TAG = this::class.simpleName
    }
}