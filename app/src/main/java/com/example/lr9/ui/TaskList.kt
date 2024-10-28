package com.example.lr9.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lr9.TaskRepository

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TaskList() {
    // Список завдань зберігається без використання ViewModel
    var tasks by rememberSaveable { mutableStateOf(TaskRepository.getTasks().toMutableList()) }

    // Лічильник виконаних завдань
    val completedCount = tasks.count { it.isCompleted }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Анімований лічильник виконаних завдань
        AnimatedVisibility(
            visible = completedCount > 0,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Text(text = "Completed tasks: $completedCount")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Відображення списку завдань
        tasks.forEach { task ->
            AnimatedVisibility(
                visible = true,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                TaskItem(
                    task = task,
                    onToggleCompletion = {
                        tasks = tasks.map { if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it }.toMutableList()
                    },
                    onDelete = {
                        tasks = tasks.filter { it.id != task.id }.toMutableList()
                    },
                    onToggleDetails = {
                        tasks = tasks.map { if (it.id == task.id) it.copy(showDetails = !it.showDetails) else it }.toMutableList()
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}