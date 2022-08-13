package com.example.konte_examen_android_2022.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.model.ToDoItem

class ToDoItemAdapter(
    private val context: Context,
    private val dataset: List<ToDoItem>
) : RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder>() {

    class ToDoItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.to_do_item_title)
        val imageView: ImageView = view.findViewById(R.id.to_do_icon)
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
    }

    // return number of items in the todoList

    override fun getItemCount() = dataset.size


}