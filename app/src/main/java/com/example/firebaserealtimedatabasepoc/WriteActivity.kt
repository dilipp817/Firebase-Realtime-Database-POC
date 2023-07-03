package com.example.firebaserealtimedatabasepoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)


        setListeners()
    }

    private fun setListeners() {
        val etData = findViewById<EditText>(R.id.etData)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            // Write a message to the database
            val database = Firebase.database
            val myRef = database.getReference("message")
            myRef.setValue(etData.text.toString())
            Toast.makeText(this, etData.text.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}