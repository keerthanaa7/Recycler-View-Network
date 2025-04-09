package com.example.recycler_view_network_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class PictureRecyclerViewAdapter(val context: Context, val list:List<Picture>) :
    RecyclerView.Adapter<PictureViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.picture_list_view, parent, false)
        return PictureViewHolder(view)

    }

    override fun getItemCount(): Int {
        System.out.println("list size " + list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
       for(picture:Picture in list){
           System.out.println("pic id " + list.get(position).id)
           holder.bind(list.get(position), context)
       }
    }

}

class PictureViewHolder(itemView: View) : ViewHolder(itemView) {

    fun bind(pic:Picture, context: Context) {
        val picture_imageView = itemView.findViewById<ImageView>(R.id.cat_picture)
        Glide.with(context)
            .load(pic.url)
            .into(picture_imageView)

        Picasso.with(context)
            .load(pic.url)
            .into(picture_imageView);

    }
}