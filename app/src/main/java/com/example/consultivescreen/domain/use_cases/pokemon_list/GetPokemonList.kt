package com.example.consultivescreen.domain.use_cases.pokemon_list

import android.util.Log
import com.example.consultivescreen.data.IpokemonRepository
import com.example.consultivescreen.domain.data.DataState
import com.example.consultivescreen.domain.model.PokemonDetailDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

/*
This is a flow based use case
 */
class GetPokemonList(
    private val repository: IpokemonRepository
) {

    val flowOfPokes = MutableStateFlow(DataState<List<PokemonDetailDomain>>())

    suspend fun execute(page: Int)  {

        try {

            withContext(Dispatchers.IO){

                flowOfPokes.emit(DataState.loading())

                val result = repository.getPokemonList(page)

                flowOfPokes.emit(result)

            }

        } catch (e: Exception) {
            Log.d("ups",e.message!!)
            flowOfPokes.emit(DataState.error<List<PokemonDetailDomain>>("Something went wrong"))
        }
    }

}
