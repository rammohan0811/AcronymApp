package com.example.acronymapp.model

import com.google.gson.annotations.SerializedName

data class AcronymResultData(
    @SerializedName("sf") val sf : String? = null,
    @SerializedName("lfs") val lfs : List<Lfs>? = null
)