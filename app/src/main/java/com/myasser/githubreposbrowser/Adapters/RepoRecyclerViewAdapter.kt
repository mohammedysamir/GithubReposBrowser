package com.myasser.githubreposbrowser.Adapters

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myasser.githubreposbrowser.R
import com.myasser.githubreposbrowser.models.Repository

class RepoRecyclerViewAdapter(private var repoList: ArrayList<Repository>) :
    RecyclerView.Adapter<RepoRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImageView: ImageView = itemView.findViewById(R.id.userImage)
        val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        val repoNameTextView: TextView = itemView.findViewById(R.id.repoNameTextView)
        val repoDescriptionTextView: TextView = itemView.findViewById(R.id.repoDescriptionTextView)
        val repoLanguageTextView: TextView = itemView.findViewById(R.id.repoLanguageTextView)
        val repoStarsTextView: TextView = itemView.findViewById(R.id.repoStarsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_card_layout, parent, false))
        //set underline to indicate clickable links
        vh.userNameTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        vh.repoNameTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repoList[position]
        //fill repo's data
        holder.repoNameTextView.text = repo.name
        holder.repoDescriptionTextView.text = repo.description
        holder.repoLanguageTextView.text = repo.language
        holder.repoStarsTextView.text = repo.stargazers_count.toString()
        holder.userNameTextView.text = repo.owner.login
        val context = holder.itemView.context
        Glide.with(context).load(Uri.parse(repo.owner.avatar_url.toString()))
            .into(holder.userImageView)
        //attach listeners
        holder.userNameTextView.setOnClickListener {
            //navigate using Repository.Owner.login
            context.startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse(repo.owner.html_url.toString())))
        }
        holder.repoNameTextView.setOnClickListener {
            //navigate using Repository.html_url
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repo.html_url.toString())))
        }
    }

    override fun getItemCount() = repoList.size

    fun setList(repoList: ArrayList<Repository>) {
        this.repoList = repoList
    }
}