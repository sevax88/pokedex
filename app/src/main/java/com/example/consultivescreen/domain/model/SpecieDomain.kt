package com.example.consultivescreen.domain.model

data class SpecieDomain(
    val name : String,
    val evolutionChain: EvolutionChainDomain?,
    val description: String,
)
