package com.bersoncrios.rickpedia.model


import com.google.gson.annotations.SerializedName

data class Chars(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)