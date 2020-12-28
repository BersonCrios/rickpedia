package com.bersoncrios.rickpedia.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.mvvm.view.listener.OnClickListener
import kotlinx.android.synthetic.main.item_episodio.view.*

class EpisodeAdapter(private val items: ArrayList<Episode>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_episodio, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =  items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickListener.onItemClickListener(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(episodes: List<Episode>){
        items.clear()
        items.addAll(episodes)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val episodeNumber = view.episodeTextView

        fun bind(episode: Episode) {
            episodeNumber.text = episode.episode
        }
    }
}