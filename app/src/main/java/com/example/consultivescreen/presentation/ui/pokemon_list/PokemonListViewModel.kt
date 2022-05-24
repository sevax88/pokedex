package com.example.consultivescreen.presentation.ui.pokemon_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consultivescreen.domain.model.PokemonDetailDomain
import com.example.consultivescreen.TAG
import com.example.consultivescreen.domain.use_cases.pokemon_list.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class PokemonListViewModel
@Inject constructor(
    private val getPokemonList : GetPokemonList,
) : ViewModel()
{
    // Pagination starts at '1' (-1 = exhausted)
    val page = mutableStateOf(1)

    val pokemons  = mutableStateOf<List<PokemonDetailDomain>>(emptyList())

    val loading = mutableStateOf(false)

    val error = mutableStateOf("")

    init {

        onTriggerEvent(PokemonListEvent.StartEvent)

        viewModelScope.launch {
            getPokemonList.flowOfPokes.collect {

                Log.d("repository", "someone change the loading state")
                loading.value = it.loading

                it.data?.let { data: List<PokemonDetailDomain> ->
                    appendPokemons(data)
                }
            }
        }
    }

    private fun appendPokemons(data: List<PokemonDetailDomain>) {
        val current = ArrayList(this.pokemons.value)
        current.addAll(data)
        this.pokemons.value = current
    }

    fun onTriggerEvent(event: PokemonListEvent) {

        viewModelScope.launch {
            try {
                when(event){
                    is PokemonListEvent.NextPageEvent -> {
                        delay(10)
                        nextPage()
                    }

                    is PokemonListEvent.StartEvent -> firstPage()
                }
            } catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
            finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }

    }

    private suspend fun firstPage(){
        getPokemonList.execute(page = page.value)
    }

    private suspend fun nextPage(){
        incrementPage()
        getPokemonList.execute(page = page.value)
    }

    private fun incrementPage(){
        setPage(page.value + 1)
    }

    private fun setPage(page: Int){
        this.page.value = page
    }


}
