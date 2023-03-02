package com.example.basecomposeproject.extension

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.basecomposeproject.utils.Constant
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun isSameDay(firstTime: Long, secondTime: Long): Boolean {
    val dateFormat = SimpleDateFormat(Constant.MM_DD_YYYY)
    return dateFormat.format(firstTime).compareTo(dateFormat.format(secondTime)) == 0
}

@RequiresApi(Build.VERSION_CODES.O)
fun compareDate(firstDate: LocalDate, secondDate: LocalDate): Boolean {
    return if (firstDate.year < secondDate.year) {
        true
    } else if (firstDate.year == secondDate.year) {
        if (firstDate.monthValue < secondDate.monthValue) {
            true
        } else if (firstDate.monthValue == secondDate.monthValue) {
            firstDate.dayOfMonth <= secondDate.dayOfMonth
        } else {
            false
        }
    } else {
        false
    }
}

fun Long.updateDate(callback: (dayText: String, dateText: String) -> Unit) {
    val dayFormat = SimpleDateFormat(Constant.EEEE)
    val dateFormat = SimpleDateFormat(Constant.MMMM_DD_YYYY)
    callback.invoke(dayFormat.format(this), dateFormat.format(this))
}
