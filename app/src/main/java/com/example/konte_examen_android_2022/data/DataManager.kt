package com.example.konte_examen_android_2022.data

import android.content.Context
import android.util.Log
import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {
    private val toDoItems = mutableListOf<ToDoItem>()

    //function which gets the todoitems from the database
    fun getTodoForToday(context: Context, finished : Int): MutableList<ToDoItem> {
        val http = WebRequestHandler()
        val db = ToDoListDatabaseManager(context, null)
        val cursor = db.getTodaysTodo()

        //loop through the cursor and add the todoitems to the list
        while (cursor.moveToNext()) {
            val priority = cursor.getInt(4)

            if(priority == 4){
                val string : String = cursor.getString(1)
                Log.i("requestString", string)
                val req = http.askQuestion(string)
                Log.i("req",req.toString())
                val toDoImage: Int = when (req) {
                    1 -> R.drawable.important_icon_64
                    2 -> R.drawable.neutral_icon_64
                    else -> R.drawable.not_important_icon_64
                }

                val obj = ToDoItem(cursor.getString(1), toDoImage, cursor.getInt(3))
                toDoItems.add(obj)
                db.modifyPriority(cursor.getString(1), req)
            }
            else{
                val toDoImage: Int = when (priority) {
                    1 -> R.drawable.important_icon_64
                    2 -> R.drawable.neutral_icon_64
                    else -> R.drawable.not_important_icon_64
                }
                val obj = ToDoItem(cursor.getString(1), toDoImage, cursor.getInt(3))
                toDoItems.add(obj)
            }

        }
        return toDoItems
    }

   fun addTodo(context: Context, todo: String) {
        val db = ToDoListDatabaseManager(context, null)
        db.addToDo(todo, 4)
        getTodoForToday(context, 0)
    }

}