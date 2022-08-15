package com.example.konte_examen_android_2022.data

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {
    private var toDoItems = mutableListOf<ToDoItem>()

    //function which gets the todoitems from the database
    fun getTodoForToday(context: Context, finished: Int): MutableList<ToDoItem> {
        val http = WebRequestHandler()
        val db = ToDoListDatabaseManager(context, null)
        val cursor = db.getTodaysTodo()

        //loop through the cursor and add the todoitems to the list
        while (cursor.moveToNext()) {
            var priority = cursor.getInt(4)
            if (priority == 4) {
                val string: String = cursor.getString(1)
                Log.i("requestString", string)
                priority = http.askQuestion(string)
                Log.i("req", priority.toString())

                db.modifyPriority(cursor.getString(1), priority)
            }
            val obj = ToDoItem(cursor.getString(1), priority, cursor.getInt(3))
            toDoItems.add(obj)


        }

        toDoItems.sortBy { it.toDoItemIconID }


        return toDoItems
    }

    fun addTodo(context: Context, todo: String): MutableList<ToDoItem> {
        val db = ToDoListDatabaseManager(context, null)
        db.addToDo(todo, 4)
        return getTodoForToday(context, 0)

    }

}