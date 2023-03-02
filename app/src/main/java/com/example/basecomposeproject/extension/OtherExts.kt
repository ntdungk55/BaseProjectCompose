package com.example.basecomposeproject.extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.O)
fun Activity.getRegionCode(): String? {
    val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.networkSpecifier
}