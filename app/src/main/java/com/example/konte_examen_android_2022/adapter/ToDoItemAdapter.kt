package com.example.konte_examen_android_2022.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
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
        val deleteMarker: ImageView = view.findViewById(R.id.delete_marker)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_item, parent, false)

        return ToDoItemViewHolder(adapterLayout)
    }

    @SuppressLint("ClickableViewAccessibility") //will make a accessibility checkmark icon if I have time
    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.toDoItemTextID
        holder.imageView.setImageResource( when (item.toDoItemIconID) {
            1 -> R.drawable.important_icon_64
            2 -> R.drawable.neutral_icon_64
            else -> R.drawable.not_important_icon_64
        }
        )
        if (item.moveToNextDay) {
            holder.deleteMarker.setImageResource(R.drawable.ic_baseline_next_day_64)
        } else {
            if (item.deleteTag) {
                holder.deleteMarker.setImageResource(R.drawable.ic_baseline_delete_forever_24)
            } else {
                holder.deleteMarker.setImageResource(R.drawable.ic_baseline_check_24)
            }
        }

        holder.deleteMarker.setOnClickListener {
            Toast.makeText(holder.card.context, item.isDone.toString(), Toast.LENGTH_SHORT).show()

            item.deleteTag = !item.deleteTag //toggle delete tag
            notifyItemChanged(position) //update the recyclerView
        }
        //lambda expression for on long click listener
        holder.deleteMarker.setOnLongClickListener {
            item.moveToNextDay = !item.moveToNextDay
            notifyItemChanged(position)
            true
        }

        holder.card.setOnClickListener {
            item.isDone = item.isDone xor 1
            if (item.isDone == 1) {
                holder.card.setCardBackgroundColor(getColor(holder.card.context,R.color.color_done))
            } else {
                holder.card.setCardBackgroundColor(getColor(holder.card.context,R.color.color_not_done))
            }
            notifyItemChanged(position)
        }
    }

    //remove item from dataset
    fun removeItem(position: Int) {
        val db : ToDoListDatabaseManager = ToDoListDatabaseManager(this.context,null)
        db.deleteToDo(dataset[position].toDoItemTextID)
        dataset.removeAt(position)
        notifyItemRemoved(position)
    }
    // return number of items in the todoList

    override fun getItemCount() = dataset.size


}