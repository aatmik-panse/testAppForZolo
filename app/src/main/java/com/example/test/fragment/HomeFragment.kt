package com.example.test.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.test.R
import com.example.test.adapter.BookListAdapter
import com.example.test.entity.ListBookEntity
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.Constants


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.BookRecyclerView)

        val queue = Volley.newRequestQueue(requireContext())
        val url = "${Constants.BASE_URL}/v0/books"
        Log.d("API Request URL", url)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.d("API Response", response.toString())
                val books = mutableListOf<ListBookEntity>()
                for (i in 0 until response.length()) {
                    val bookObject = response.getJSONObject(i)

                    val bookId = bookObject.getInt("id")
                    val bookTitle = bookObject.getString("name")
                    val bookStatus = bookObject.getString("status")

                    if(bookStatus=="AVAILABLE"){
                    books.add(ListBookEntity(bookId, bookTitle, bookStatus)
                )}
                }
                Log.d("Parsed Books", "Number of books fetched: ${books.size}")


                val adapter = BookListAdapter(books)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged() // Ensures the adapter knows the data has changed

            },
            { error ->
                Log.e("API Error", error.toString())
                Log.e("VolleyExample", "Error: $error")
            }
        )

        queue.add(jsonArrayRequest)
    }

    companion object {
    }
}