package com.example.konte_examen_android_2022.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ToDoItem(
    @StringRes val toDoItemTextID: Int,
    @DrawableRes val toDoItemIconID: Int
)

