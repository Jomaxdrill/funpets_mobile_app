package com.example.funpets_mb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funpets_mb.R
import com.example.funpets_mb.model.Comments

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.ViewHolder> () {

    var listComments = ArrayList<Comments>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_comments,parent,false))

    override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {
        val comment = listComments[position]
        holder.tvCommentUser.text = comment.user
        holder.tvCommentScore.text = comment.score
        holder.tvCommentComment.text = comment.comment
    }

    override fun getItemCount() = listComments.size

    fun updateData(data: List<Comments>) {
        listComments.clear()
        listComments.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCommentUser = itemView.findViewById<TextView>(R.id.tvItemCommentsUser)
        val tvCommentScore = itemView.findViewById<TextView>(R.id.tvItemCommentsScore)
        val tvCommentComment = itemView.findViewById<TextView>(R.id.tvItemCommentsComment)
    }
}