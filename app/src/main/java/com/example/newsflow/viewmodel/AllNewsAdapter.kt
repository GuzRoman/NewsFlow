package com.example.newsflow.viewmodel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsflow.R
import com.example.newsflow.model.models.Article


class AllNewsAdapter: RecyclerView.Adapter<AllNewsAdapter.AllNewsViewHolder>() {

    val news = mutableListOf<Article>()

    class AllNewsViewHolder(item: View): RecyclerView.ViewHolder(item){
        var title: TextView = item.findViewById(R.id.titleNameItem)
        var textItem: TextView = item.findViewById(R.id.textItem)
        var image: ImageView = item.findViewById(R.id.imageItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_news_item, parent, false)
        return AllNewsViewHolder(view)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        val currentNews = news[position]
        holder.title.text = currentNews.title
        holder.textItem.text = currentNews.description

        Glide.with(holder.itemView.context).load(currentNews.urlToImage).into(holder.image)
    }

    fun setData(setNews: List<Article>){
        news.addAll(setNews)
        notifyDataSetChanged()
    }

}