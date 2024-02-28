package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.api.ListBookEntity
import com.example.test.databinding.BooklistBinding

class BookListAdapter(private val books: MutableList<ListBookEntity>) : RecyclerView.Adapter<BookListAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        return RowViewHolder(BooklistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book.name, book.status)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateData(newBooks: MutableList<ListBookEntity>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    class RowViewHolder(private val binding: BooklistBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, price: String) {
            binding.textView4.text = item
            binding.textView3.text = price
        }
    }
}
