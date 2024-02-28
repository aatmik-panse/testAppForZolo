package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.test.api.ApiCall
import com.example.test.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var booksView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HomeFragment()
//        booksView = findViewById(R.id.fragmentContainerView4) // Replace with your actual TextView ID

        var NavController = findNavController(R.id.fragmentContainerView4)
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
        bottomNav.setupWithNavController(NavController)

        val queue = Volley.newRequestQueue(this)

        val url = "http://192.168.38.207:8080/v0/books"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                // Process the JSON array (list of books)
                for (i in 0 until response.length()) {
                    val bookObject = response.getJSONObject(i)

                    // Extract book details
                    val bookId = bookObject.getInt("id")
                    val bookTitle = bookObject.getString("name")
                    val bookAuthor = bookObject.getString("status")

                    // Perform actions with book details (e.g., display in UI)
                    Log.d(
                        "VolleyExample",
                        "Book ID: $bookId, Title: $bookTitle, Author: $bookAuthor"
                    )
                }
            },
            { error ->
                // Handle error
                Log.e("VolleyExample", "Error: $error")
            }
        )

        // Add the request to the queue
        queue.add(jsonArrayRequest)

    }
}
