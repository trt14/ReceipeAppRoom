package com.example.recipeapproom

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapproom.data.DataDatabase
import com.example.noteapproom.data.Recipe
import android.widget.LinearLayout




class MainPage : AppCompatActivity() {

    lateinit var recycle: RecyclerView
    lateinit var list:List<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_receipe)
        recycle=findViewById(R.id.rv)
        list= listOf()
        updatedrecycle()
    }

    fun addrecipe(view: View) {
        startActivity( Intent(this,MainActivity::class.java))

    }
    fun updatedrecycle(){
        val ob= DataDatabase.getinstant(applicationContext)
        var r=ob.DataDao().getAllUserInfo()
        recycle.adapter = RecycleView (this@MainPage, r)
        recycle.layoutManager = LinearLayoutManager(this)


    }

    fun deleteitem(recipe: Recipe) {
        val ob= DataDatabase.getinstant(applicationContext)
        ob.DataDao().deleterecipe(recipe)
        updatedrecycle()

        Toast.makeText(applicationContext,"Successfully deleted", Toast.LENGTH_SHORT).show()

    }


    fun UpdateRec(recipe:Recipe) {
        val ob= DataDatabase.getinstant(applicationContext)
        var c=recipe
        val d = AlertDialog.Builder(this)
        d.setTitle("Update Celebrity")


        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        var input1 = EditText(this)
        input1.setText(c.title)
        layout.addView(input1) // Notice this is an add method
        val input2 = EditText(this)
        input2.setText(c.author)
        layout.addView(input2)
        val input3 = EditText(this)
        input3.setText(c.ingredents)
        layout.addView(input3)
        val input4 = EditText(this)
        input4.setText(c.instruction)
        layout.addView(input4)


        d.setView(layout)


        d.setPositiveButton("update") {dialogInterface, i ->
            if(input4.text.isNotEmpty() && input3.text.isNotEmpty() && input2.text.isNotEmpty() && input1.text.isNotEmpty()) {


                c.title = input1.text.toString()
                c.author = input2.text.toString()
                c.ingredents = input3.text.toString()
                c.instruction = input4.text.toString()
                ob.DataDao()
                    .updaterecipe(Recipe(c.id, c.title, c.author, c.ingredents, c.instruction))
                Toast.makeText(applicationContext, "data successfully Edited", Toast.LENGTH_SHORT)
                    .show()

                updatedrecycle()
            }else{
                Toast.makeText(applicationContext, "Field is empty", Toast.LENGTH_SHORT)
                    .show()

            }
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }

d.show()

    }


    fun confirm(recipe: Recipe){
        var at= AlertDialog.Builder(this)
        at.setTitle("delete Celebrity")
        at.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
            deleteitem(recipe)
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }

}