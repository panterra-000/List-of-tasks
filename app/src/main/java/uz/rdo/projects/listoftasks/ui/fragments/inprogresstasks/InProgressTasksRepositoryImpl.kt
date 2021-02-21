package uz.rdo.projects.listoftasks.ui.fragments.inprogresstasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import uz.rdo.projects.listoftasks.data.repositories.InProgressTasksRepository
import uz.rdo.projects.listoftasks.data.room.dao.TaskModelDao
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.coroutines.Coroutines
import uz.rdo.projects.listoftasks.utils.local.ResultData
import javax.inject.Inject


class InProgressTasksRepositoryImpl @Inject constructor(
    private val dao: TaskModelDao
) : InProgressTasksRepository {
    override fun getAllInProgressTasks(): LiveData<ResultData<List<TaskModel>>> {
        val resultLiveData = MediatorLiveData<ResultData<List<TaskModel>>>()
        Coroutines.ioThenMain(
            { dao.getInProgressTaskModels() },
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

    override fun getAllTasks(): LiveData<ResultData<List<TaskModel>>> {
        val resultLiveData = MediatorLiveData<ResultData<List<TaskModel>>>()
        Coroutines.ioThenMain(
            { dao.getAllTaskModels() },
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

    override fun deleteInProgressTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
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

    override fun updateInProgressTask(taskModel: TaskModel): LiveData<ResultData<Boolean>> {
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