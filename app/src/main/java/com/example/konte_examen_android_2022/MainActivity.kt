package com.example.konte_examen_android_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.adapter.ToDoItemAdapter
import com.example.konte_examen_android_2022.data.DataManager
import javax.sql.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = DataManager().getTodoForToday()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ToDoItemAdapter(this, myDataset)

        recyclerView.setHasFixedSize(true) // makes sure content dont change the size of recyclerview
    }
}