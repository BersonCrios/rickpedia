package com.bersoncrios.rickpedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.network.ConfigureRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodeViewModel: ViewModel() {
    private val service = ConfigureRetrofit()
    private val _episode = MutableLiveData<Episode>()

    val episode: LiveData<Episode>
        get() = _episode

    fun fetchDetailEpisode(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            val res = service.getEpisodeDetails(id)
            if (res.isSuccessful){
                withContext(Dispatchers.Main){
                    _episode.value = res.body()
                }
            }
        }
    }
}