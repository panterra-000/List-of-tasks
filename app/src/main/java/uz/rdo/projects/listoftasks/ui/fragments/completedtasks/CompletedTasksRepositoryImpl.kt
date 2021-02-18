package uz.rdo.projects.listoftasks.ui.fragments.completedtasks

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.repositories.CompletedTasksRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

class CompletedTasksRepositoryImpl:CompletedTasksRepository {
    override fun getAllCompletedTasks(): LiveData<ResultData<List<TaskModel>>> {
        TODO("Not yet implemented")
    }

    override fun deleteCompletedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun updateCompletedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun deleteAllCompletedTasks(): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }
}