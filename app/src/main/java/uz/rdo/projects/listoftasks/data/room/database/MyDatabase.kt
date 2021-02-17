package uz.rdo.projects.listoftasks.data.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.rdo.projects.listoftasks.app.App
import uz.rdo.projects.listoftasks.data.room.dao.TaskModelDao
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel


@Database(entities = [TaskModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun taskModelDao(): TaskModelDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.instance.applicationContext,
                    MyDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}