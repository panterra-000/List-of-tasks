package uz.rdo.projects.listoftasks.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> MediatorLiveData<*>.addSourceDisposable(liveData: LiveData<T>, onChange: (T) -> Unit): MediatorLiveData<*> {
    addSource(liveData) {
        onChange(it)
        removeSource(liveData)
    }
    return this
}

fun <T> MediatorLiveData<*>.addSourceDisposableNoAction(liveData: LiveData<T>): MediatorLiveData<*> {
    addSource(liveData) {
        removeSource(liveData)
    }
    return this
}