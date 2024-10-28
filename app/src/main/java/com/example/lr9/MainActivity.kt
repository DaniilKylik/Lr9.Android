package com.example.lr9

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.lr9.ui.TaskListScreen
import com.example.lr9.ui.TaskList
import com.example.lr9.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TaskListScreen() // Відображення списку завдань з використанням ViewModel
                // TaskListScreen() // Відображення списку завдань з без використанням ViewModel
            }
        }
    }
}