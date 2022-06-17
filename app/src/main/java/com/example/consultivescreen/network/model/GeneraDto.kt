package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class GeneraDto(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: LanguageXDto
)