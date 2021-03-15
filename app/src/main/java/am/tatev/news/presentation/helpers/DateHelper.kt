package am.tatev.news.presentation.helpers

import java.text.SimpleDateFormat
import java.util.*

private const val MILLISECONDS_IN_SECOND = 1000

fun Long.toHumanReadableDate(): String =
    try {
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm", Locale.getDefault())
        val date = Date(this * MILLISECONDS_IN_SECOND)
        sdf.format(date)
    } catch (e: Exception) {
        this.toString()
    }
