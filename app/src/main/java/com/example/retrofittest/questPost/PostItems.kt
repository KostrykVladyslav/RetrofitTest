package com.example.retrofittest.questPost

import com.google.gson.annotations.SerializedName

data class PostItems(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val text: String
)

