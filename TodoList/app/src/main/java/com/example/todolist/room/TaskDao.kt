package com.example.todolist.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query(value = "Select * FROM task_table ORDER BY id ASC")
    fun readAllTasks(): LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query(value = "DELETE FROM task_table")
    suspend fun deleteAll()
}