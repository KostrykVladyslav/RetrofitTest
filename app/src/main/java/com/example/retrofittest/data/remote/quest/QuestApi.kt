 package com.example.retrofittest.data.remote.quest

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface QuestApi {
    @GET("./getQuestList")
    @Headers("Content-Type: application/json;charset=utf-8")
    fun getQuestList(): Single<QuestListResponse>
}