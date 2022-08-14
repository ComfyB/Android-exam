package com.example.konte_examen_android_2022

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.adapter.ToDoItemAdapter
import com.example.konte_examen_android_2022.data.DataManager
import com.example.konte_examen_android_2022.data.ToDoListDatabaseManager
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the layout
        setContentView(R.layout.activity_main)



        //initialize the data manager
        val myDataset = DataManager().getTodoForToday(this,0)

        //initialize the recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //initialize the adapter to the recycler view
        recyclerView.adapter = ToDoItemAdapter(this, myDataset)
        // makes sure content don't change the size of recyclerview
        recyclerView.setHasFixedSize(true)
    }


}