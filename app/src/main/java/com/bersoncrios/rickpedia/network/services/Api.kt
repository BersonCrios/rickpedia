package com.bersoncrios.rickpedia.network.services

import com.bersoncrios.rickpedia.model.Chars
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.model.completeLocation.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("character")
    suspend fun getCharacter(): Response<Chars>

    @GET("character")
    suspend fun getLocation(): Response<Location>

    @GET("character/{id}")
    suspend fun getDetailsChar(@Path("id") id: Int): Response<Result>

    @GET("episode/{id}")
    suspend fun getEpisodeDetails(@Path("id") id: Int): Response<Episode>
}