package uz.rdo.projects.listoftasks.data.repositories

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

interface InProgressTasksRepository {
    fun getAllInProgressTasks(): LiveData<ResultData<List<TaskModel>>>
    fun getAllTasks(): LiveData<ResultData<List<TaskModel>>>
    fun deleteInProgressTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun updateInProgressTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
}