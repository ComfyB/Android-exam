package com.example.konte_examen_android_2022.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoListDatabaseManager(context: Context, factory:SQLiteDatabase.CursorFactory?) :  SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)  {


    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME ($TODO_ID INTEGER PRIMARY KEY,$TASK_TEXT TEXT, $TASK_DATE TEXT)")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addToDo(text : String, date : String){
        val values = ContentValues()
        values.put("TODO_ID", text)
        values.put("TASK_DATE", date)

        val db = this.writableDatabase
        db.insert(TODO_ID, null, values)

        db.close();
    }

    fun getToDo() : Cursor{
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getTodaysTodo(date : String):Cursor{
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $TASK_DATE LIKE $date", null)
    }

    companion object{
        private val DATABASE_NAME = "TODO_LIST_DATABASE"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "TODOS"
        private val TODO_ID = "TODO_ID"
        private val TASK_TEXT = "TASK_TEXT"
        private val TASK_DATE = "TASK_DATE"
    }
}
