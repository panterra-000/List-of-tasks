package uz.rdo.projects.listoftasks.utils.time

import java.text.SimpleDateFormat
import java.util.*


fun Date.toMyString(
    format: String = "dd/MM/yyyy",
    locale: Locale = Locale.getDefault()
): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}