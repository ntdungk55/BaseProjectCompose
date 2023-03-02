package com.example.basecomposeproject.extension

import java.util.*

fun Calendar.plus(timeInMillis: Long): Calendar {
    setTimeInMillis(this.timeInMillis + timeInMillis)
    return this
}

fun Calendar.minus(timeInMillis: Long): Calendar {
    setTimeInMillis(this.timeInMillis - timeInMillis)
    return this
}

fun Calendar.EEE_MMMM_d_yyyy(): String {
    val locale = Locale.getDefault()
    val dayInWeek = getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG_FORMAT, locale)
    val month = getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, locale)
    val day = get(Calendar.DAY_OF_MONTH)
    val year = get(Calendar.YEAR)

    return  "$dayInWeek, $month $day, $year"
}

fun Calendar.ddMMMyyyy(): String {
    val locale = Locale.getDefault()
    val month = getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, locale)
    val day = get(Calendar.DAY_OF_MONTH)
    val year = get(Calendar.YEAR)

    return  "$day-$month-$year"
}

fun Calendar.ddMMyyyy(): String {
    val locale = Locale.getDefault()
    val month = get(Calendar.MONTH)
    val day = get(Calendar.DAY_OF_MONTH)
    val year = get(Calendar.YEAR)

    return  "$day-$month-$year"
}

fun Calendar.timeInDay(): String {
    val hours = get(Calendar.HOUR)
    val minutes = get(Calendar.MINUTE)
    val amPm = get(Calendar.AM_PM)

    return "$hours:" +
            "${minutes.toString().padStart(2, '0')} ${if(amPm == 0) "AM" else "PM"}"
}

fun Calendar.hour(hour: Int = -1): Int {
    return if(hour == -1) get(Calendar.HOUR_OF_DAY)
    else {
        set(Calendar.HOUR_OF_DAY, hour)
        hour
    }
}

fun Calendar.minute(minute: Int = -1): Int {
    return if(minute == -1) get(Calendar.MINUTE)
    else {
        set(Calendar.MINUTE, minute)

        minute
    }
}

fun Calendar.year(year: Int = -1): Int {
    return if(year == -1) get(Calendar.YEAR)
    else {
        set(Calendar.YEAR, year)

        year
    }
}

fun Calendar.month(month: Int = -1): Int {
    return if(month == -1) get(Calendar.MONTH)
    else {
        set(Calendar.MONTH, month)

        month
    }
}

fun Calendar.day(day: Int = -1): Int {
    return if(day == -1) get(Calendar.DAY_OF_MONTH)
    else {
        set(Calendar.DAY_OF_MONTH, day)

        day
    }
}

fun Long.time(): String {
    return Calendar.getInstance(Locale.getDefault()).apply {
        timeInMillis = this@time
    }.let {
        "${it.ddMMyyyy()} ${it.timeInDay()}"
    }
}