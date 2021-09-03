package com.example.todolist.room

import androidx.lifecycle.LiveData


class TaskRepository(private val taskDao: TaskDao) {

    val readAllTasks:LiveData<List<Task>> = taskDao.readAllTasks()

    suspend fun addTask(task: Task) { taskDao.addTask(task) }

    suspend fun updateTask(task: Task) { taskDao.updateTask(task) }

    suspend fun deleteTask(task: Task) { taskDao.deleteTask(task) }

    suspend fun deleteAll() { taskDao.deleteAll() }
}