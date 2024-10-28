package com.example.lr9.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*

@Composable
fun ThemeSwitcher() {
    var isDarkTheme by remember { mutableStateOf(false) }

    // Перемикач теми для візуальної зміни стилю інтерфейсу
    Button(onClick = { isDarkTheme = !isDarkTheme }) {
        Text(text = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme")
    }

    // Відображення теми на основі стану перемикача
    MaterialTheme(colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()) {
    }
}