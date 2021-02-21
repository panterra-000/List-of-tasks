package uz.rdo.projects.listoftasks.ui.fragments.completedtasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import uz.rdo.projects.listoftasks.data.repositories.CompletedTasksRepository
import uz.rdo.projects.listoftasks.data.room.dao.TaskModelDao
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.coroutines.Coroutines
import uz.rdo.projects.listoftasks.utils.local.ResultData
import javax.inject.Inject

class CompletedTasksRepositoryImpl @Inject constructor(
    private val dao: TaskModelDao
) : CompletedTasksRepository {

    override fun getAllCompletedTasks(): LiveData<ResultData<List<TaskModel>>> {
        val resultLiveData = MediatorLiveData<ResultData<List<TaskModel>>>()
        Coroutines.ioThenMain(
            { dao.getCompletedTaskModels() },
            { list ->
                if (list != null)
                    if (list.isNotEmpty())
                        resultLiveData.value = ResultData.data(list)
                    else {
                        resultLiveData.value = ResultData.data(listOf())
                    }
                else
                    resultLiveData.value = ResultData.message("непредвиденная ошибка")
            }
        )
        return resultLiveData
    }

    override fun deleteCompletedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        val resultLiveData = MutableLiveData<ResultData<Boolean>>()

        Coroutines.ioThenMain(
            { dao.delete(taskModel) },
            { status ->
                if (status != null)
                    if (status > 0)
                        resultLiveData.value = ResultData.data(true)
                    else
                        resultLiveData.value = ResultData.data(false)
                else
                    resultLiveData.value = ResultData.message("непредвиденная ошибка")
            }
        )
        return resultLiveData
    }

    override fun updateCompletedTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
        val resultLiveData = MutableLiveData<ResultData<Boolean>>()
        Coroutines.ioThenMain(
            { dao.update(taskModel) },
            { status ->
                if (status != null)
                    if (status > 0)
                        resultLiveData.value = ResultData.data(true)
                    else
                        resultLiveData.value = ResultData.data(false)
                else
                    resultLiveData.value = ResultData.message("непредвиденная ошибка")
            }
        )
        return resultLiveData
    }

}