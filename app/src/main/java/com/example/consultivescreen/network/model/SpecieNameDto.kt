package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class SpecieNameDto(
    @SerializedName("language")
    val language: LanguageXXDto,
    @SerializedName("name")
    val name: String
)