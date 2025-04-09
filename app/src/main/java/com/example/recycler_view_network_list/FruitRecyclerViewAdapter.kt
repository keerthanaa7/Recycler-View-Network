package com.example.recycler_view_network_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class FruitRecyclerViewAdapter(val context: Context, val list:List<String>) :
    RecyclerView.Adapter<FruitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_fruit_item, parent, false)
        return FruitViewHolder(view)
    }

    override fun getItemCount(): Int {
        System.out.println("fruit list size" +list.size)
      return list.size
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        for(element:String in list){
            holder.bind(list.get(position))
        }

    }
}

class FruitViewHolder(itemView: View) : ViewHolder(itemView) {

    fun bind(element:String) {
        val fruit_tv = itemView.findViewById<TextView>(R.id.fruit_tv)
        fruit_tv.text = element
    }
}