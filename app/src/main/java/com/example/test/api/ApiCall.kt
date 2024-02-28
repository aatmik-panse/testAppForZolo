package com.example.test.api

import android.widget.Toast
import com.example.test.fragment.HomeFragment
import retrofit.*

public class ApiCall {
    fun getBooks(context: HomeFragment, callback: (List<ListBookEntity>) -> Unit) {

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://192.168.17.207:8080/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call: Call<List<ListBookEntity>> = service.fetchAllBooks()

        // Use the enqueue() method of the Call object to
        // make an asynchronous API request.
        call.enqueue(object : Callback<List<ListBookEntity>> {
            // This is an anonymous inner class that implements the Callback interface.
            override fun onResponse(
                response: Response<List<ListBookEntity>>?,
                retrofit: Retrofit?
            ) {
                // This method is called when the API response is received successfully.

                if(response!!.isSuccess){
                    // If the response is successful, parse the
                    // response body to a ListBookEntity object.
                    val books: List<ListBookEntity> = response.body() as List<ListBookEntity>


                    // Call the callback function with the ListBookEntity
                    // object as a parameter.
                    callback(books)
                }
            }

            override fun onFailure(t: Throwable?) {
                // This method is called when the API request fails.
//                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
