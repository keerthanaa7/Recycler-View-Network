package com.example.recycler_view_network_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RecyclerViewAdapter(val context: Context, val list:List<Developer>): RecyclerView.Adapter<myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflator = LayoutInflater.from(context)
        val myView = layoutInflator.inflate(R.layout.list_item, parent, false)
        return myViewHolder(myView)
    }

    override fun getItemCount(): Int {
        System.out.println("list size " + list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        for(dev:Developer in list){
            holder.bind(dev, position)
        }
    }
}

class myViewHolder(val view:View): ViewHolder(view) {

    fun bind(developer: Developer, position: Int) {
        val nameTvView = view.findViewById<TextView>(R.id.node_id_tv)
        val loginTvView = view.findViewById<TextView>(R.id.login_tv)
        val idTvView = view.findViewById<TextView>(R.id.id_tv)
        nameTvView.text = developer.node_id + position.toString()
        loginTvView.text = developer.login
        idTvView.text = developer.id
    }

}