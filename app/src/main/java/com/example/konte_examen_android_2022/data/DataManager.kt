package com.example.konte_examen_android_2022.data

import android.content.Context
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {

    fun getTodoForToday(context : Context): List<ToDoItem> {
        val db  = ToDoListDatabaseManager(context, null)
        val cursor = db.getToDo()
        while(!cursor.isLast){
           cursor.getString(cursor.position)
            cursor.moveToNext()
        }


        return listOf(
        )
    }
}