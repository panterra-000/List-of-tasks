package uz.rdo.projects.listoftasks.ui.fragments.alltasks

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.repositories.AllTasksRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

class AllTasksRepositoryImpl:AllTasksRepository {
    override fun getAllTasks(): LiveData<ResultData<List<TaskModel>>> {
        TODO("Not yet implemented")
    }

    override fun deleteTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun updateTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun deleteAllTasks(): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }
}