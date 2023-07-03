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
                    // Check if the dataSnapshot exists
                    if (dataSnapshot.exists()) {
                        // Read the shop_landing_page array
                        val shopLandingPageList = dataSnapshot.child("shop_landing_page").children
                        for (landingPageSnapshot in shopLandingPageList) {
                            // Read the section
                            val section = landingPageSnapshot.child("section").value as String
                            println("Section: $section")

                            // Read the items array
                            val itemsList = landingPageSnapshot.child("items").children
                            for (itemSnapshot in itemsList) {
                                // Read the item details
                                val title = itemSnapshot.child("title").value as String
                                val url = itemSnapshot.child("url").value as String
                                val imageUrl = itemSnapshot.child("imageUrl").value as String
                                val category = itemSnapshot.child("category").value as String

                                println("Title: $title")
                                println("URL: $url")
                                println("Image URL: $imageUrl")
                                println("Category: $category")
                            }
                        }

                        // Read the recipe_detail_page array
                        val recipeDetailPageList = dataSnapshot.child("recipe_detail_page").children
                        for (detailPageSnapshot in recipeDetailPageList) {
                            // Read the section
                            val section = detailPageSnapshot.child("section").value as String
                            println("Section: $section")

                            // Read the items array
                            val itemsList = detailPageSnapshot.child("items").children
                            for (itemSnapshot in itemsList) {
                                // Read the item details
                                val title = itemSnapshot.child("title").value as String
                                val url = itemSnapshot.child("url").value as String
                                val imageUrl = itemSnapshot.child("imageUrl").value as String
                                val category = itemSnapshot.child("category").value as String

                                println("Title: $title")
                                println("URL: $url")
                                println("Image URL: $imageUrl")
                                println("Category: $category")
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                    Toast.makeText(this@ReadActivity, "Failed to read value.", Toast.LENGTH_SHORT).show()

                }
            })
    }
}