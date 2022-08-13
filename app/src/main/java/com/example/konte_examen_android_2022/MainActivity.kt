package com.example.konte_examen_android_2022

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.adapter.ToDoItemAdapter
import com.example.konte_examen_android_2022.data.DataManager
import com.example.konte_examen_android_2022.data.ToDoListDatabaseManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val myDataset = DataManager().getTodoForToday(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ToDoItemAdapter(this, myDataset)

        recyclerView.setHasFixedSize(true) // makes sure content don't change the size of recyclerview}

    }
}