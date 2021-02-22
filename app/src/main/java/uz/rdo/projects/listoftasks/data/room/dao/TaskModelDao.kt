package uz.rdo.projects.listoftasks.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel

@Dao
interface TaskModelDao : BaseDao<TaskModel> {
    @Query("SELECT * FROM taskmodel")
    fun getAllTaskModels(): List<TaskModel>

    @Query("SELECT * FROM taskmodel WHERE status ='completed'")
    fun getCompletedTaskModels(): List<TaskModel>

    @Query("SELECT * FROM taskmodel WHERE status ='in_progress'")
    fun getInProgressTaskModels(): List<TaskModel>

    @Query("DELETE FROM taskmodel")
    fun deleteAllTasks(): Int
}