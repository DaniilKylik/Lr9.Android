package com.example.lr9

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // Список завдань, який зберігає свій стан у SavedStateHandle
    private val _tasks = MutableStateFlow(savedStateHandle["tasks"] ?: TaskRepository.getTasks())
    val tasks: StateFlow<List<Task>> get() = _tasks

    init {
        // Збереження початкового стану списку завдань
        savedStateHandle["tasks"] = _tasks.value
    }

    // Зміна статусу виконання завдання, збереження стану у SavedStateHandle
    fun toggleTaskCompletion(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(isCompleted = !task.isCompleted) else task
        }
        savedStateHandle["tasks"] = _tasks.value
    }

    // Перемикання стану деталей завдання
    fun toggleTaskDetails(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(showDetails = !task.showDetails) else task
        }
        savedStateHandle["tasks"] = _tasks.value
    }

    // Видалення завдання з анімацією та збереженням стану
    fun deleteTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
        savedStateHandle["tasks"] = _tasks.value
    }

    // Лічильник виконаних завдань
    fun countCompletedTasks(): Int {
        return _tasks.value.count { it.isCompleted }
    }
}