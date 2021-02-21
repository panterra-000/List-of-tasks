package uz.rdo.projects.listoftasks.data.repositories

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

interface CompletedTasksRepository {
    fun getAllCompletedTasks(): LiveData<ResultData<List<TaskModel>>>
    fun getAllTasks(): LiveData<ResultData<List<TaskModel>>>
    fun deleteCompletedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun updateCompletedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
}