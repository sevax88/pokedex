package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class PalParkEncounterDto(
    @SerializedName("area")
    val area: AreaDto,
    @SerializedName("base_score")
    val baseScore: Int,
    @SerializedName("rate")
    val rate: Int
)