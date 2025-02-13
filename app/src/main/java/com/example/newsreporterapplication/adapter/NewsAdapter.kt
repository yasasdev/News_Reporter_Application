package com.example.newsreporterapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsreporterapplication.databinding.NoteLayoutBinding
import com.example.newsreporterapplication.fragments.HomeFragmentDirections
import com.example.newsreporterapplication.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.newsDescription == newItem.newsDescription &&
                    oldItem.newsTitle == newItem.newsTitle &&
                    oldItem.newsImageUrl == newItem.newsImageUrl
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = differ.currentList[position]

        holder.itemBinding.noteTitle.text = currentNews.newsTitle
        holder.itemBinding.noteDesc.text = currentNews.newsDescription

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragment2ToEditNoteFragment2(currentNews)
            it.findNavController().navigate(direction)
        }
    }
}