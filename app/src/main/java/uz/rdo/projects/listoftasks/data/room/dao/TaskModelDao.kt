package uz.rdo.projects.listoftasks.data.room.dao

import androidx.room.Query
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel

interface TaskModelDao : BaseDao<TaskModel> {
    @Query("SELECT * FROM taskmodel")
    fun getAllPlaceModels(): List<TaskModel>
}