package com.us.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class spNewsAdapter(private val listener: sportsNews): RecyclerView.Adapter<spNewsAdapter.spNewsViewHolder>() {
    private val item : ArrayList<Data> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): spNewsViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        val viewHolder = spNewsViewHolder(view)
        view.setOnClickListener{

            listener.onItemClicked(item[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: spNewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageURL).into(holder.image)

    }

    override fun getItemCount(): Int {
        return item.size
    }
    fun updateNews(updatedNews : ArrayList<Data>){
        item.clear()
        item.addAll(updatedNews)

        notifyDataSetChanged()
    }

    class spNewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView : TextView = itemView.findViewById(R.id.title)
        val author : TextView = itemView.findViewById(R.id.author)
        val image : ImageView = itemView.findViewById(R.id.image)

    }
}
    interface spItemClicked {
        fun onItemClicked(item : Data)
}