package com.example.konte_examen_android_2022.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.R
import com.example.konte_examen_android_2022.data.ToDoListDatabaseManager
import com.example.konte_examen_android_2022.model.ToDoItem
import com.google.android.material.card.MaterialCardView


class ToDoItemAdapter(
    private val context: Context,
    private val dataset: MutableList<ToDoItem>
) : RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder>() {

    class ToDoItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.to_do_item_title)
        val imageView: ImageView = view.findViewById(R.id.to_do_icon)
        val card: MaterialCardView = view.findViewById(R.id.material_card_todo)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_item, parent, false)

        return ToDoItemViewHolder(adapterLayout)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.toDoItemTextID
        holder.imageView.setImageResource(item.toDoItemIconID)
        holder.card.setOnClickListener {
            Toast.makeText(holder.card.context, item.isDone.toString(), Toast.LENGTH_SHORT).show()
        }
        //lambda expression for on long click listener
        holder.card.setOnLongClickListener {
            Toast.makeText(holder.card.context, "swipe left to change!", Toast.LENGTH_SHORT).show()
            true
        }
        //expression for on swipe listener from left
        holder.card.setOnTouchListener { v, event ->
            if (event.action == android.view.MotionEvent.ACTION_DOWN) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                Toast.makeText(
                    holder.card.context,
                    item.toDoItemTextID + " removed!",
                    Toast.LENGTH_SHORT
                ).show()
                val db = ToDoListDatabaseManager(holder.card.context, null)
                db.modifyIsDoneState(
                    item.toDoItemTextID, item.isDone
                )
                removeItem(position)
            }
            false
        }


    }

    //remove item from dataset
    fun removeItem(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
    }
    // return number of items in the todoList

    override fun getItemCount() = dataset.size


}