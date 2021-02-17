package uz.rdo.projects.listoftasks.data.repositories

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

interface PerformedTasksRepository {
    fun getAllPerformedTasks(): LiveData<ResultData<List<TaskModel>>>
    fun deletePerformedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun updatePerformedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun deleteAllPerformedTasks(): LiveData<ResultData<Boolean>>
}