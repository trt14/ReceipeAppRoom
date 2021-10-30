package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.noteapproom.data.DataDatabase
import com.example.noteapproom.data.Recipe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        val author = findViewById<View>(R.id.editTextTextPersonName3) as EditText
        val inge = findViewById<View>(R.id.editTextTextPersonName4) as EditText
        val ins = findViewById<View>(R.id.editTextTextPersonName5) as EditText
        val savebtn = findViewById<View>(R.id.button) as Button

        val ob= DataDatabase.getinstant(applicationContext)

        savebtn.setOnClickListener {
            var s1=title.text.toString()
            var s2=author.text.toString()
            var s3=inge.text.toString()
            var s4=ins.text.toString()
            if(s1.isNotEmpty()&& s2.isNotEmpty() && s3.isNotEmpty()&& s4.isNotEmpty())
            {
                ob.DataDao().insertrecipe(Recipe(0,s1,s2,s3,s4))
                title.text.clear()
                author.text.clear()
                inge.text.clear()
                ins.text.clear()
                Toast.makeText(applicationContext, "data successfully added", Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                Toast.makeText(applicationContext,"one or more field empty",Toast.LENGTH_SHORT).show()
            }


        }

    }

    fun viewreceipe(view: View) {
        startActivity(Intent(this,MainPage::class.java))
    }
}
