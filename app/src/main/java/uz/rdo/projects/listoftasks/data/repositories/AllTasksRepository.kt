package uz.rdo.projects.listoftasks.data.repositories

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData


interface AllTasksRepository {
    fun getAllTasks(): LiveData<ResultData<List<TaskModel>>>
    fun deleteTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun updateTask(taskModel: TaskModel): LiveData<ResultData<Boolean>>
    fun deleteAllTasks(): LiveData<ResultData<Boolean>>
}






