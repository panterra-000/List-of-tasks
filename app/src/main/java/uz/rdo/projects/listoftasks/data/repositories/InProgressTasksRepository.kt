package uz.rdo.projects.listoftasks.data.repositories

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

interface InProgressTasksRepository {
    fun getAllInPssTasks(): LiveData<ResultData<List<TaskModel>>>
    fun deleteInPssTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun updateInPssTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
}