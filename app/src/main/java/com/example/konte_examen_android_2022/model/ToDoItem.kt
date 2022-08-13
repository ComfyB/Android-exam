package com.example.konte_examen_android_2022.model

import androidx.annotation.DrawableRes

data class ToDoItem(
    val toDoItemTextID: String,
    @DrawableRes val toDoItemIconID: Int,
    val isDone: Int
)

