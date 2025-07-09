package com.example.tdahelper.core

const val hourMeasure = 1
const val dayMeasure = 12
const val weekMeasure = 7
const val monthMeasure = 4
const val yearMeasure = 12

fun Int.hoursToDays() = this / dayMeasure
fun Int.daysToHours() = this * dayMeasure
fun Int.daysToWeeks() = this / weekMeasure
fun Int.weeksToDays() = this * weekMeasure
fun Int.weeksToMonths() = this / monthMeasure
fun Int.monthsToWeeks() = this * monthMeasure
fun Int.monthsToYears() = this / yearMeasure
fun Int.yearsToMonths() = this * yearMeasure

val dayWithHours: (counter: Int, days: Int)-> Int = {c, d ->
    d*dayMeasure + c
}


val logTime: (Int, Int)->String = {counter, days->
    val weeks = days.daysToWeeks()
    val months = weeks.weeksToMonths()
    val years = months.monthsToYears()
    "$years(year) $months(month) $weeks(week) $days(day) - $counter"
}

enum class DurationType {
    HOUR,DAY,WEEK,MONTH,YEAR
}

data class Duration(
    val time: Int,
    val durationType: DurationType,
    val plus: Duration? = null
){
    fun parse(): Int {
        return when (durationType) {
            DurationType.HOUR -> time
            DurationType.DAY -> time.daysToHours()
            DurationType.WEEK -> time.weeksToDays().daysToHours()
            DurationType.MONTH -> time.monthsToWeeks().weeksToDays().daysToHours()
            DurationType.YEAR -> time.yearsToMonths().monthsToWeeks().weeksToDays().daysToHours()
        } + (plus?.parse() ?: 0)
    }
}