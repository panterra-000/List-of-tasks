package uz.rdo.projects.listoftasks.ui.fragments.alltasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.rdo.projects.listoftasks.data.repositories.AllTasksRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.extensions.addSourceDisposable

class AllTasksViewModel @ViewModelInject constructor(
    private val repository: AllTasksRepository
) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _allTasks = MediatorLiveData<List<TaskModel>>()
    val allTasks: LiveData<List<TaskModel>> get() = _allTasks

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> get() = _delete


    fun getAllTasks() {
        _allTasks.addSourceDisposable(repository.getAllTasks()) { resultData ->
            resultData.onData { list ->
                if (list.isEmpty()) _message.value = "Список сейчас пуст"
                _allTasks.value = list
            }.onMessage { message ->
                _message.value = message
            }
        }
    }
}