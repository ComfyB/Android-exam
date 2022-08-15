package com.example.konte_examen_android_2022.model

import androidx.annotation.DrawableRes

data class ToDoItem(
    val toDoItemTextID: String,
    val toDoItemIconID: Int,
    var isDone: Int,
    var deleteTag: Boolean = false,
    var moveToNextDay : Boolean = false
) {

}

