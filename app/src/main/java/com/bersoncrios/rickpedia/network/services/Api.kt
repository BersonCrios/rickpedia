package com.bersoncrios.rickpedia.network.services

import com.bersoncrios.rickpedia.model.Chars
import com.bersoncrios.rickpedia.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("character")
    suspend fun getCharacter(): Response<Chars>

    @GET("character/{id}")
    suspend fun getDetailsChar(@Path("id") id: Int): Response<Result>
}