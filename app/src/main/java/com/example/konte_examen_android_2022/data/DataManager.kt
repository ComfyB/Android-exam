package com.example.konte_examen_android_2022.data

import android.content.Context
import com.example.konte_examen_android_2022.model.ToDoItem

class DataManager {
    private var toDoItems = mutableListOf<ToDoItem>()

    //function which gets the todoitems from the database
    fun getTodoForToday(context: Context, finished: Int): MutableList<ToDoItem> {
        val http = WebRequestHandler() //create a new webrequesthandler object
        val db = ToDoListDatabaseManager(context, null)
        val cursor = db.getTodaysTodo() //  get the todoitems from the database

        //loop through the cursor and add the todoitems to the list
        while (cursor.moveToNext()) {
            var priority = cursor.getInt(4)
            if (priority == 4) {
                val string: String = cursor.getString(1)
                priority = http.askQuestion(string) //get the priority from the API
                db.modifyPriority(
                    cursor.getString(1),
                    priority
                ) //update the priority in the database
            }
            val obj = ToDoItem(cursor.getString(1), priority, cursor.getInt(3))
            toDoItems.add(obj)
        }

        toDoItems.sortBy { it.toDoItemIconID } //sort the list by the icon id


        return toDoItems
    }

    fun addTodo(
        context: Context,
        todo: String
    ): MutableList<ToDoItem> { //add a todoitem to the database
        val db = ToDoListDatabaseManager(context, null)
        db.addToDo(todo, 4)
        return getTodoForToday(context, 0)

    }


}