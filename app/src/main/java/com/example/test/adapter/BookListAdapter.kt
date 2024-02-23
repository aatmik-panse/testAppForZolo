package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.BooklistBinding

class BookListAdapter( private val items:List<String>,private val price:List<String>,private val image: List<Int>): RecyclerView.Adapter<BookListAdapter.RowViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        return  RowViewHolder(BooklistBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }


    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(item,price, images)
    }


    override fun getItemCount(): Int {
        return items.size
    }


    class RowViewHolder (private val binding: BooklistBinding) : RecyclerView.ViewHolder(binding.root){
        val imagesView = binding.imageView
        fun bind(item: String,price: String,images: Int) {
            binding.textView4.text = item
            binding.textView3.text = price
            imagesView.setImageResource(images)
        }

    }
}