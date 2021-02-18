package uz.rdo.projects.listoftasks.ui.fragments.inprogresstasks

import androidx.lifecycle.LiveData
import uz.rdo.projects.listoftasks.data.repositories.InProgressTasksRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.local.ResultData

class InProgressTasksRepositoryImpl private constructor(): InProgressTasksRepository {
    override fun getAllInPssTasks(): LiveData<ResultData<List<TaskModel>>> {
        TODO("Not yet implemented")
    }

    override fun deleteInPssTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun updateInPssTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInPssTasks(): LiveData<ResultData<Boolean>> {
        TODO("Not yet implemented")
    }
}