package com.example.konte_examen_android_2022

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.konte_examen_android_2022.adapter.ToDoItemAdapter
import com.example.konte_examen_android_2022.data.DataManager
import com.example.konte_examen_android_2022.data.ToDoListDatabaseManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the layout
        setContentView(R.layout.activity_main)
        testData() //load some testData

        //initialize the data manager
        val myDataset = DataManager().getTodoForToday(this, 0)

        //initialize the recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //initialize the adapter to the recycler view
        val myAdapter = ToDoItemAdapter(this, myDataset)
        recyclerView.adapter = myAdapter

        // makes sure content don't change the size of recyclerview
        recyclerView.setHasFixedSize(true)
        //initialize the floating action button
        val addTodo =
            findViewById<FloatingActionButton>(R.id.floating_add_button)  //initialize the add button
        val todoTextField =
            findViewById<TextInputLayout>(R.id.text_input_new_todo)     //initialize the text field
        todoTextField.isFocusableInTouchMode = true //make the text field focusable

        addTodo.setOnClickListener {
            todoTextField.visibility = TextInputLayout.VISIBLE  //show the text field
            addTodo.visibility = FloatingActionButton.GONE  //hide the button
            todoTextField?.requestFocusFromTouch()  //focus the text field
            val lManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            lManager.showSoftInput(todoTextField, 0)
        }

        fun leaveFocus() {
            closeKeyBoard(todoTextField.windowToken)    //close the keyboard
            todoTextField.visibility = TextInputLayout.GONE //hide the text field
            addTodo.visibility = FloatingActionButton.VISIBLE   //show the button
        }

        fun saveInput() {
            val todoText =
                todoTextField.editText?.text.toString()  //get the text from the text field
            if (todoText.isNotEmpty()) {    //if the text is not empty
                //add the todo to the database
                // ugly hack to make the recycler view update when i add a new item.
                // would have done this better if i thought of it earlier. :/
                recyclerView.adapter = ToDoItemAdapter(this, DataManager().addTodo(this, todoText))

                todoTextField.editText?.text?.clear()   //clear the text field
            }
            leaveFocus()
        }

        todoTextField.setEndIconOnClickListener {
            saveInput()
        }
        todoTextField.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) { //if the enter key is pressed
                saveInput()
                leaveFocus()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val confirmButton: MaterialButton = findViewById(R.id.button_bottom_todo_list)
        //when clicked, update the db with whats completed etc.
        confirmButton.setOnClickListener {
            val db = ToDoListDatabaseManager(this, null)
            val items = myAdapter.getItems() //get the items from the adapter
            for (item in items) {  //for each item in the list But it doesn't get the data from the item in the recyclerview after you update it by clicking so it doesnt work.
                if (item.isDone == 1) {
                    db.modifyIsDoneState(
                        item.toDoItemTextID,
                        0
                    ) // i flipped the isDone in the function a bit bad design
                } else if (item.moveToNextDay) {
                    db.moveToDoToTomorrow(item.toDoItemTextID)
                }
            }
            recyclerView.adapter = ToDoItemAdapter(this, DataManager().getTodoForToday(this, 0))
        }
    }

    private fun closeKeyBoard(token: IBinder) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager //get the input method manager
        imm.hideSoftInputFromWindow(token, 0) //hide the keyboard
    }


    private fun testData() {
        val db: ToDoListDatabaseManager = ToDoListDatabaseManager(this, null)
        db.addToDo("lorem ip", 4)
        db.addToDo("lorem kom", 4)
        db.addToDo("vakse gangen ip", 4)
        db.addToDo("spise is ip", 4)
        db.addToDo("lang stor ip", 4)
        db.addToDo("finne en ny eksmaen ip", 4)
    }
}