package uz.rdo.projects.listoftasks.ui.fragments.completedtasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.rdo.projects.listoftasks.data.repositories.CompletedTasksRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.extensions.addSourceDisposable

class CompletedTaskViewModel @ViewModelInject() constructor(
    private val repository: CompletedTasksRepository
) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _allCompletedTasks = MediatorLiveData<List<TaskModel>>()
    val allCompletedTasks: LiveData<List<TaskModel>> get() = _allCompletedTasks

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> get() = _delete

    private val _update = MutableLiveData<Boolean>()
    val update: LiveData<Boolean> get() = _update

    fun getAllCompletedTasks() {
        _allCompletedTasks.addSourceDisposable(repository.getAllCompletedTasks()) { resultData ->
            resultData.onData { list ->
                if (list.isEmpty()) _message.value = "Список сейчас пуст"
                _allCompletedTasks.value = list
            }.onMessage { message ->
                _message.value = message
            }
        }
    }

    fun deleteTask(taskModel: TaskModel) {
        _allCompletedTasks.addSourceDisposable(repository.deleteCompletedTask(taskModel)) { resultData ->
            resultData.onData { status ->
                _delete.value = status
                getAllCompletedTasks()
            }.onMessage { message ->
                _message.value = message
            }
        }
    }




}