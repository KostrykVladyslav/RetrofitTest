package com.example.retrofittest.questPost

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.R

class PostAdapter(private val postItems: List<PostItems>): RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postItems.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bind(postItems[position])
    }

}

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val textId = itemView.findViewById<TextView>(R.id.textId)
    private val textUserId = itemView.findViewById<TextView>(R.id.textUserId)
    private val textTitle = itemView.findViewById<TextView>(R.id.textTitle)
    private val textDescription = itemView.findViewById<TextView>(R.id.textDescription)

    @SuppressLint("SetTextI18n")
    fun bind(postItems: PostItems){
        textId.text = "ID: " + postItems.id
        textUserId.text = "User: " + postItems.userId
        textTitle.text = postItems.title
        textDescription.text = postItems.text
    }
}