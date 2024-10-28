package com.example.lr9

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false, // Статус виконання завдання
    var showDetails: Boolean = false  // Стан показу деталей завдання
)