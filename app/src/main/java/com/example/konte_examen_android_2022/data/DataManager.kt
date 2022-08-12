package com.example.konte_examen_android_2022.data

import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {

    fun getTodoForToday(): List<ToDoItem> {
        return listOf(
            ToDoItem(R.string.ToDo1, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo2, R.drawable.important_icon_64)
        )
    }
}