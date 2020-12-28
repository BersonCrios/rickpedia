package com.bersoncrios.rickpedia.mvvm.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.base.BaseActivity
import com.bersoncrios.rickpedia.helper.Extra.Companion.extractExtras
import com.bersoncrios.rickpedia.main.ListMenuUi
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.mvvm.view.adapter.EpisodeAdapter
import com.bersoncrios.rickpedia.mvvm.view.dialog.EpisodeDetailsDialog
import com.bersoncrios.rickpedia.mvvm.view.listener.OnClickListener
import com.bersoncrios.rickpedia.mvvm.viewmodel.CharacterViewModel
import com.bersoncrios.rickpedia.mvvm.viewmodel.EpisodeViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.layout_char_details_activity.*

class CharDetailsActivity : BaseActivity(), OnClickListener {

    private lateinit var charsViewModel: CharacterViewModel
    private lateinit var episodeViewModel: EpisodeViewModel

    private val episodeAdapter = EpisodeAdapter(arrayListOf(), this)

    private var idExtractedOfStringList: MutableList<Int> = arrayListOf()
    private var episodeList: MutableList<Episode> = arrayListOf()

    private var idEpList: List<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_char_details_activity)

        configRecyclerView()
        val id: Int = extractExtras(intent)
        findDetails(id)
    }

    private fun configRecyclerView() {
        episodiosRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodeAdapter
        }
    }

    private fun findDetails(id: Int) {
        charsViewModel = ViewModelProvider(this)
            .get(CharacterViewModel::class.java)

        charsViewModel.fetchDetailsOfChars(id)

        charsViewModel.details.observe(this, Observer { char ->
            dataOfScreen(char)
            listaDeEpisodios(char)
            idEpList = getListEpisodeId(char)
            clickListenes(char)
            findEpisodes(idEpList)
        })
    }

    private fun getListEpisodeId(char: Result): List<Int> {
        for (ep in listaDeEpisodios(char)) {
            idExtractedOfStringList.add(ep.substring(40, ep.length).toInt())
        }
        return idExtractedOfStringList
    }

    private fun listaDeEpisodios(char: Result): List<String> {
        return char.episode
    }

    private fun findEpisodes(idList: List<Int>) {
        episodeViewModel = ViewModelProvider(this)
            .get(EpisodeViewModel::class.java)

        findIdAndFetchDetailsEpisode(idList)

        episodeViewModel.episode.observe(this, Observer { episode ->
            episodeList.add(episode)
            episodeAdapter.update(episodeList)
        })
    }

    private fun findIdAndFetchDetailsEpisode(idList: List<Int>) {
        for (id in idList) {
            episodeViewModel.fetchDetailEpisode(id)
        }
    }

    private fun dataOfScreen(char: Result) {
        nomeTextView.text = char.name
        sexoTextView.text = char.gender
        statusTextView.text = char.status
        especieTextView.text = char.species
        originTextView.text = char.origin.name
        locationTextView.text = char.location.name
        Glide.with(applicationContext)
            .load(char.image)
            .override(500, 500)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(fotoImageView)
    }


    private fun clickListenes(char: Result) {
        originTextView.setOnClickListener {
            Log.e("origin", char.origin.url)
        }
        locationTextView.setOnClickListener {
            Log.e("location", char.location.url)
        }
    }

    override fun onItemClickListener(result: Result) {}

    override fun onItemClickListener(episode: Episode) {
        EpisodeDetailsDialog(this, episode.name, episode.airDate, episode.episode).show()
    }

    override fun onItemClickListener(menu: ListMenuUi) {}
}