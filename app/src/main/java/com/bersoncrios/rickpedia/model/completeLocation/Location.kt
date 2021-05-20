package com.bersoncrios.rickpedia.model.completeLocation

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val resultLocations: List<ResultLocation>
)