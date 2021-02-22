package uz.rdo.projects.listoftasks.data.local

import android.content.Context
import android.content.SharedPreferences
import uz.rdo.projects.listoftasks.utils.StringPreference

class LocalStorage private constructor(context: Context) {
    companion object {
        @Volatile
        lateinit var instance: LocalStorage; private set

        fun init(context: Context) {
            synchronized(this) {
                instance = LocalStorage(context)
            }
        }
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    var language: String by StringPreference(pref, "uz")
    var langLocal: String by StringPreference(pref)


}