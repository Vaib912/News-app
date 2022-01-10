 package com.us.newsapp

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

 class Adapter(private val listener: Indian_News): RecyclerView.Adapter<Adapter.inNewsViewHolder>() {
    private val item : ArrayList<Data> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): inNewsViewHolder {
val view =  LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        val viewHolder = inNewsViewHolder(view)
        view.setOnClickListener{

           listener.onItemClicked(item[viewHolder.absoluteAdapterPosition])
       }
        return viewHolder
    }

    override fun onBindViewHolder(holder: inNewsViewHolder, position: Int) {
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

    class inNewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView : TextView = itemView.findViewById(R.id.title)

        val author : TextView= itemView.findViewById(R.id.author)
        val image : ImageView = itemView.findViewById(R.id.image)

    }
}
interface inItemClicked {
    fun onItemClicked(item : Data)
}