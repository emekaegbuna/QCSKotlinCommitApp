package com.example.qcsproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qcsproject.R
import com.example.qcsproject.model.KotlinCommitModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_commit.view.*

class KotlinCommitAdapter(val kotlinCommitModel: List<KotlinCommitModel>) : RecyclerView.Adapter<KotlinCommitAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_commit, parent, false))
    }

    override fun getItemCount(): Int {
        return kotlinCommitModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kotlinCommit = kotlinCommitModel[position]

        holder.title.text = kotlinCommit.commit.message

        Picasso.get().load(kotlinCommit.author.avatar_url).into(holder.image)
        holder.name.text = kotlinCommit.commit.commit_author.name
        holder.time_date.text = kotlinCommit.commit.commit_author.date
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var title = itemView.tv_commit_title
        var image = itemView.iv_author_image
        var name = itemView.tv_author_name
        var time_date = itemView.tv_commit_time_date
    }
}