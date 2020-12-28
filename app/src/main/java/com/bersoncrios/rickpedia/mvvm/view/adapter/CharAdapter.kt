package com.bersoncrios.rickpedia.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.mvvm.view.listener.OnClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_character.view.*

class CharAdapter(private val items: ArrayList<Result>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<CharAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickListener.onItemClickListener(item)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    fun update(chars: List<Result>) {
        items.clear()
        items.addAll(chars)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val charName = view.nomeTextView
        private val charsSpecie = view.especieTexView
        private val charImage = view.fotoImageView

        fun bind(char: Result) {
            charName.text = char.name
            charsSpecie.text = char.species
            Glide.with(itemView.context)
                .load(char.image)
                .override(300, 200)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(charImage)
        }
    }
}
