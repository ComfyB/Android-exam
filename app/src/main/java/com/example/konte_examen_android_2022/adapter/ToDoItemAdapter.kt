package com.example.konte_examen_android_2022.adapter

import android.app.Application
import android.content.Context
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.data.ToDoListDatabaseManager
import com.example.konte_examen_android_2022.model.ToDoItem
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.NonDisposableHandle.parent

class ToDoItemAdapter(
    private val context: Context,
    private val dataset: List<ToDoItem>
) : RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder>() {

    class ToDoItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.to_do_item_title)
        val imageView: ImageView = view.findViewById(R.id.to_do_icon)
        val card: MaterialCardView = view.findViewById(R.id.material_card_todo)

    }

    // create new views for the
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_item, parent, false)

        return ToDoItemViewHolder(adapterLayout)
    }


    //replacing the content of the view. called by
    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.toDoItemTextID
        holder.imageView.setImageResource(item.toDoItemIconID)
        holder.card.setOnClickListener {
            val db = ToDoListDatabaseManager(holder.card.context, null)
            db.modifyIsDoneState(
                item.toDoItemTextID, item.isDone
            )
            Toast.makeText(holder.card.context, "Hello", Toast.LENGTH_SHORT).show()
        }

    }
    // return number of items in the todoList

    override fun getItemCount() = dataset.size


}