package com.example.firebaserealtimedatabasepoc

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.firebaserealtimedatabasepoc.data.Item
import com.example.firebaserealtimedatabasepoc.data.RecipeDetailPage
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.json.JSONObject

class ReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        setListeners()

    }

    private fun setListeners() {
        val tvReadDb = findViewById<TextView>(R.id.tvReadDb)
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<HashMap<String, Item>>()
                    val textToShow = (value?.get("recipe_detail_page") as ArrayList<RecipeDetailPage>)[0]
                    tvReadDb.text = "textToShow"
                    Toast.makeText(this@ReadActivity, value.toString(), Toast.LENGTH_SHORT).show()
                    val dfd = emptySet<String>()
                    val fdf = setOf<String>()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                    Toast.makeText(this@ReadActivity, "Failed to read value.", Toast.LENGTH_SHORT).show()

                }
            })
    }
}