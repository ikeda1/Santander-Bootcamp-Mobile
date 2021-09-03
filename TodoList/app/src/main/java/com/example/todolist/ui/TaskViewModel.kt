package com.example.todolist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.room.Task
import com.example.todolist.room.TaskDatabase
import com.example.todolist.room.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val readAllTasks: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllTasks = repository.readAllTasks
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { repository.addTask(task) }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateTask(task) }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteTask(task) }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteAll()}
    }

}