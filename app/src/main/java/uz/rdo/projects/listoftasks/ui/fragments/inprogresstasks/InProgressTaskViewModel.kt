package uz.rdo.projects.listoftasks.ui.fragments.inprogresstasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.rdo.projects.listoftasks.data.repositories.CompletedTasksRepository
import uz.rdo.projects.listoftasks.data.repositories.InProgressTasksRepository
import uz.rdo.projects.listoftasks.data.room.entities.TaskModel
import uz.rdo.projects.listoftasks.utils.extensions.addSourceDisposable


class InProgressTaskViewModel @ViewModelInject() constructor(
    private val repository: InProgressTasksRepository
) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _allInProgressTasks = MediatorLiveData<List<TaskModel>>()
    val allInProgressTasks: LiveData<List<TaskModel>> get() = _allInProgressTasks

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> get() = _delete

    private val _update = MutableLiveData<Boolean>()
    val update: LiveData<Boolean> get() = _update

    private val _allTasks = MediatorLiveData<List<TaskModel>>()
    val allTasks: LiveData<List<TaskModel>> get() = _allTasks


    fun getAllInProgressTasks() {
        _allInProgressTasks.addSourceDisposable(repository.getAllInProgressTasks()) { resultData ->
            resultData.onData { list ->
                if (list.isEmpty()) _message.value = "Список сейчас пуст"
                _allInProgressTasks.value = list
            }.onMessage { message ->
                _message.value = message
            }
        }
    }

    fun getAllTasks() {
        _allTasks.addSourceDisposable(repository.getAllTasks()) { allTasks ->
            allTasks.onData { __allTasks ->
                _allTasks.value = __allTasks
            }.onMessage { message ->
                _message.value = message
            }
        }
    }

    fun deleteTask(taskModel: TaskModel) {
        _allInProgressTasks.addSourceDisposable(repository.deleteInProgressTask(taskModel)) { resultData ->
            resultData.onData { status ->
                _delete.value = status
                getAllTasks()
                getAllInProgressTasks()
            }.onMessage { message ->
                _message.value = message
            }
        }
    }

    fun updateTask(taskModel: TaskModel) {
        _allInProgressTasks.addSourceDisposable(repository.updateInProgressTask(taskModel)) { resultData ->
            resultData.onData { status ->
                _update.value = status
            }.onMessage { message ->
                _message.value = message
            }
        }
    }
}