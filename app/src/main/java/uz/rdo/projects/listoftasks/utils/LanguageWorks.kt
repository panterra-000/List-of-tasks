package uz.rdo.projects.listoftasks.utils

import android.content.Context
import android.preference.PreferenceManager

fun getLanguage(context: Context): String {
    var change = ""
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val language = sharedPreferences.getString("language", "bak")
    change = when (language) {
        "Russian" -> {
            "ru"
        }
        "English" -> {
            "en"
        }
        else -> {
            ""
        }
    }
    return change
}

/*change language ... */