package com.example.test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.adapter.BookListAdapter
import com.example.test.databinding.BooklistBinding
import com.example.test.databinding.FragmentHomeBinding


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
        val author = listOf("Pradyut","Steve","Ashneer","rameshs","yogesh")
        val price = listOf("fnjfn","njhfvn","jkfnc","jnc","kjncj")
        val cover = listOf(R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image4)

        val adapter = BookListAdapter(author,price,cover)


        binding.BookRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.BookRecyclerView.adapter = adapter
    }
    companion object {

    }
}