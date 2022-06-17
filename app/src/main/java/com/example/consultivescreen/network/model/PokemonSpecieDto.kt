package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class PokemonSpecieDto(

    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,

    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainDto,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: Any?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryDto>,
    @SerializedName("form_descriptions")
    val formDescriptions: List<Any>,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean,
    @SerializedName("gender_rate")
    val genderRate: Int,


    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean,
    @SerializedName("hatch_counter")
    val hatchCounter: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,

)