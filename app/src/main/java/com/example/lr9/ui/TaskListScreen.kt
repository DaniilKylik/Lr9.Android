package com.example.lr9.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lr9.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel = viewModel()) {
    // Отримання стану списку завдань з ViewModel
    val tasks by viewModel.tasks.collectAsState()
    val completedCount = viewModel.countCompletedTasks() // Лічильник виконаних завдань

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Анімоване відображення лічильника виконаних завдань
        AnimatedVisibility(
            visible = completedCount > 0, // Лічильник відображається, якщо є виконані завдання
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Text(text = "Completed tasks: $completedCount")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Відображення списку завдань із використанням TaskItem
        tasks.forEach { task ->
            AnimatedVisibility(
                visible = true, // Керування видимістю завдання при видаленні
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                TaskItem(
                    task = task,
                    onToggleCompletion = { viewModel.toggleTaskCompletion(task.id) },
                    onDelete = { viewModel.deleteTask(task.id) },
                    onToggleDetails = { viewModel.toggleTaskDetails(task.id) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}