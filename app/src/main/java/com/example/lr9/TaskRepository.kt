package com.example.lr9

object TaskRepository {
    fun getTasks(): List<Task> {
        return listOf(
            Task(1, "Task 1", "Description for Task 1"),
            Task(2, "Task 2", "Description for Task 2"),
            Task(3, "Task 3", "Description for Task 3"),
        )
    }
}