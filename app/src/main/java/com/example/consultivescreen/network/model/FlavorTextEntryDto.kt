package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class FlavorTextEntryDto(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: LanguageDto,
    @SerializedName("version")
    val version: VersionDto
)