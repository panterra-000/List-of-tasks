package uz.rdo.projects.listoftasks.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import uz.rdo.projects.listoftasks.data.room.dao.TaskModelDao
import uz.rdo.projects.listoftasks.data.room.database.MyDatabase
import javax.inject.Singleton

/**
 *  Created by Davronbek Raximjanov 17-Feb-2021
 **/

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getDataBase(): MyDatabase = MyDatabase.getDatabase()

    @Provides
    @Singleton
    fun getTaskModelDao(appDatabase: MyDatabase): TaskModelDao = appDatabase.taskModelDao()


}