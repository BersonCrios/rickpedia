package com.bersoncrios.rickpedia.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.mvvm.view.listener.OnClickListener
import kotlinx.android.synthetic.main.item_menu.view.*

class ListMenuAdapter(private val items: ArrayList<ListMenuUi>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<ListMenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =  items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickListener.onItemClickListener(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(menu: List<ListMenuUi>){
        items.clear()
        items.addAll(menu)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val titulo = view.itemMenuTv

        fun bind(menu: ListMenuUi) {
            titulo.text = menu.titulo
        }
    }
}