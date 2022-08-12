package com.example.konte_examen_android_2022.data

import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {

    fun getTodoForToday(): List<ToDoItem> {
        return listOf(
            ToDoItem(R.string.ToDo1, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo2, R.drawable.neutral_icon_64),
            ToDoItem(R.string.ToDo3, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo4, R.drawable.not_important_icon_64),
            ToDoItem(R.string.ToDo5, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo6, R.drawable.neutral_icon_64),
            ToDoItem(R.string.ToDo7, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo1, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo3, R.drawable.important_icon_64),
            ToDoItem(R.string.ToDo2, R.drawable.important_icon_64)
        )
    }
}