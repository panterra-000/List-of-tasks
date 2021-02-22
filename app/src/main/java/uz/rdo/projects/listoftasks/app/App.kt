package uz.rdo.projects.listoftasks.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.rdo.projects.listoftasks.data.local.LocalStorage
import uz.rdo.projects.listoftasks.ui.mainactivity.MainActivity
import uz.rdo.projects.listoftasks.utils.getLanguage
import java.util.*

/**
 * Created by Raximjanov Davronbek on 17-Feb-21
 **/

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        LocalStorage.init(this)
    }

    companion object {
        lateinit var instance: App
    }
}