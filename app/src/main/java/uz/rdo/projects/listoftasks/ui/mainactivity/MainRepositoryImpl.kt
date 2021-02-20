package uz.rdo.projects.listoftasks.ui.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.rdo.projects.listoftasks.data.repositories.MainRepository
import uz.rdo.projects.listoftasks.data.room.dao.TaskModelDao
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.coroutines.Coroutines
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: TaskModelDao
):MainRepository {

    override fun insertTaskToDB(taskModel: TaskModel): LiveData<Boolean> {
        val resultLiveData = MutableLiveData<Boolean>()
        Coroutines.ioThenMain(
            { dao.insert(taskModel) },
            { id ->
                if (id != null)
                    resultLiveData.value = id > 0
            }
        )
        return resultLiveData
    }

}