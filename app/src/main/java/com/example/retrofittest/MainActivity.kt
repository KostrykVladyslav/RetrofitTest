package com.example.retrofittest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.retrofittest.questPost.JsonPlaceholderApi
import com.example.retrofittest.questPost.PostAdapter
import com.example.retrofittest.questPost.PostItems
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpLoggingIterator = HttpLoggingInterceptor()
        httpLoggingIterator.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingIterator)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)

        val call = jsonPlaceholderApi.getPosts()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclePostAdapter)
        call.enqueue(object : Callback<List<PostItems>> {
            override fun onFailure(call: Call<List<PostItems>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<PostItems>>,
                response: Response<List<PostItems>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = PostAdapter(response.body()!!)
                    }
                }
            }
        })

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(this, "Page updated!", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = true
        }
    }
}


