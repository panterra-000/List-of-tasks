package uz.rdo.projects.listoftasks.ui.mainactivity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import uz.rdo.projects.listoftasks.data.repositories.MainRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.extensions.addSourceDisposable

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _resultLiveData = MediatorLiveData<Boolean>()
    val resultLiveData: LiveData<Boolean> get() = _resultLiveData

    fun addPlaceModelToDB(taskModel: TaskModel) {
        _resultLiveData.addSourceDisposable(repository.insertTaskToDB(taskModel)) { isAdded ->
            _resultLiveData.value = isAdded
        }
    }

}