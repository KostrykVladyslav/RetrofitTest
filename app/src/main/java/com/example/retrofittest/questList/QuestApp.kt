package com.example.retrofittest.questList

import android.app.Application
import com.example.retrofittest.data.remote.quest.QuestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuestApp: Application() {

    lateinit var questApi: QuestApi

    override fun onCreate() {
        super.onCreate()
        configureRetrofit()
    }

    private fun configureRetrofit(){
        val httpLoggingIterator = HttpLoggingInterceptor()
        httpLoggingIterator.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingIterator)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://guestgo.getsandbox.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        questApi = retrofit.create(QuestApi::class.java)
    }
}

