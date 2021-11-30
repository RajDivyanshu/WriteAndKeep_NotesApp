package com.kotlinninja.writeandkeepnotes.news.adapter_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinninja.writeandkeepnotes.news.fragment_news.NewsMainActivity
import com.kotlinninja.writeandkeepnotes.databinding.ItemNotesBinding

import com.kotlinninja.writeandkeepnotes.news.model_news.News

class NewsListAdapter(private val listener: NewsMainActivity, val items:ArrayList<News>) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder{
        return NewsListAdapter.NewsViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

//    private val items: ArrayList<News> = ArrayList()

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
//        val viewHolder = NewsViewHolder(view)
//
//        view.setOnClickListener {
//            listener.onItemClicked(items[viewHolder.adapterPosition])
//        }
//
//        return viewHolder
//    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.titleView.text = currentItem.title
        holder.binding.name.text = currentItem.publishedAt
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.binding.)
    }

//        holder.rowContent.setOnClickListener {



//    fun updateNews(updatedNews: ArrayList<News>){
//        items.clear()
//        items.addAll(updatedNews)
//    }

    override fun getItemCount(): Int {
        return items.size
    }





}

//class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val titleView: TextView = itemView.findViewById(R.id.txtTitle)
//    val image: ImageView = itemView.findViewById(R.id.imgNews)
//    val author: TextView = itemView.findViewById(R.id.txtAuthor)
//}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}