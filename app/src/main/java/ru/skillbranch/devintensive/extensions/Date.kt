package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
/*
0с - 1с "только что"
1с - 45с "несколько секунд назад"
45с - 75с "минуту назад"
75с - 45мин "N минут назад"
45мин - 75мин "час назад"
75мин 22ч "N часов назад"
22ч - 26ч "день назад"
26ч - 360д "N дней назад"
>360д "более года назад"
*/
    val dateDiff = date.time - this.time

    if(dateDiff > 0) {
        return when (dateDiff) {
            in 0..1 * SECOND              -> "только что"
            in    1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in   45 * SECOND..75 * SECOND -> "минуту назад"
            in   75 * SECOND..45 * MINUTE -> "${TimeUnits.MINUTE.plural(abs(dateDiff / MINUTE).toInt())} назад"
            in   45 * MINUTE..75 * MINUTE -> "час назад"
            in   75 * MINUTE..22 * HOUR   -> "${TimeUnits.HOUR.plural(abs(dateDiff / HOUR).toInt())} назад"
            in   22 * HOUR..26   * HOUR   -> "день назад"
            in   26 * HOUR..360  * DAY    -> "${TimeUnits.DAY.plural(abs(dateDiff / DAY).toInt())} назад"
            in  360 * DAY..99999 * DAY    -> "более чем через год"
            else -> "ошибка $dateDiff"
        }
    }
    else {
        return when (dateDiff) {
            in 0..1 * SECOND              -> "только что"
            in    1 * SECOND..45 * SECOND -> "через несколько секунд"
            in   45 * SECOND..75 * SECOND -> "через минуту"
            in   75 * SECOND..45 * MINUTE -> "через ${TimeUnits.MINUTE.plural(abs(dateDiff / MINUTE).toInt())}"
            in   45 * MINUTE..75 * MINUTE -> "через час"
            in   75 * MINUTE..22 * HOUR   -> "через ${TimeUnits.HOUR.plural(abs(dateDiff / HOUR).toInt())}"
            in   22 * HOUR..26   * HOUR   -> "через день"
            in   26 * HOUR..360  * DAY    -> "через ${TimeUnits.DAY.plural(abs(dateDiff / DAY).toInt())}"
            in  360 * DAY..99999 * DAY    -> "более года назад"
            else -> "ошибка $dateDiff"
        }
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return "$value " + when {
            value == 0 || value == 11 || value == 12 || value == 13 || value == 14 ||
            (value % 10) in 5..9 -> {
                when(this) {
                    SECOND -> "секунд"
                    MINUTE -> "минут"
                    HOUR   -> "часов"
                    DAY    -> "дней"
                }
            }
            (value % 10) == 1 -> {
                when(this) {
                    SECOND -> "секунду"
                    MINUTE -> "минуту"
                    HOUR   -> "час"
                    DAY    -> "день"
                }
            }
            (value % 10) in 2..4 -> {
                when(this) {
                    SECOND -> "секунды"
                    MINUTE -> "минуты"
                    HOUR   -> "часа"
                    DAY    -> "дня"
                }
            }
            else -> ""
        }
    }
}