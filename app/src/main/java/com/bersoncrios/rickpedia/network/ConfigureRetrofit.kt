package com.bersoncrios.rickpedia.network

import com.bersoncrios.rickpedia.model.Chars
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.model.completeLocation.Location
import com.bersoncrios.rickpedia.network.services.Api
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigureRetrofit {
    private val api: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    suspend fun getChars(): Response<Chars> = api.getCharacter()
    suspend fun getLocations(): Response<Location> = api.getLocation()
    suspend fun  getCharsDetail(id: Int): Response<Result> = api.getDetailsChar(id)
    suspend fun  getEpisodeDetails(id: Int): Response<Episode> = api.getEpisodeDetails(id)

    companion object {
        const val BASE_URL: String = "https://rickandmortyapi.com/api/"
    }
}

