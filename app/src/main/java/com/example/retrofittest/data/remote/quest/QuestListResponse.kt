package com.example.retrofittest.data.remote.quest

import com.example.retrofittest.data.remote.quest.common.RemoteListItem
import com.google.gson.annotations.SerializedName

data class QuestListResponse(val items: List<QuestListItems>)

data class QuestListItems(

    @SerializedName("questId")
    val questId: String,

    @SerializedName("questName")
    val name: String,

    @SerializedName("questSubtitle")
    val subTitle: String,

    @SerializedName("questImage")
    val questImage: String,

    @SerializedName("description")
    val items: List<RemoteListItem>
)