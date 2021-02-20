package uz.rdo.projects.listoftasks.data.repositories

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

interface MainRepository {
 /*   fun deleteAllTasks(): LiveData<ResultData<Boolean>>
    fun deleteAllCompletedTasks(): LiveData<ResultData<Boolean>>*/
    fun insertTaskToDB(taskModel: TaskModel): LiveData<Boolean>
}