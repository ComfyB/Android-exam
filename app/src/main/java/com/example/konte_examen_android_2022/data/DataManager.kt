package com.example.konte_examen_android_2022.data

import android.content.Context
import android.util.Log
import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {

    fun getTodoForToday(context: Context): MutableList<ToDoItem> {
        val http = WebRequestHandler()
        val db = ToDoListDatabaseManager(context, null)
        val cursor = db.getToDo()
        val list = mutableListOf<ToDoItem>()

        while (cursor.moveToNext()) {
            val req = http.askQuestion(cursor.getString(1))
            Log.i("req",req.toString())
            val toDoImage: Int = when (req) {
                1 -> R.drawable.important_icon_64
                2 -> R.drawable.neutral_icon_64
                else -> R.drawable.not_important_icon_64

            }
            val obj = ToDoItem(cursor.getString(1), toDoImage)

            list.add(obj)
        }
        return list
    }
}