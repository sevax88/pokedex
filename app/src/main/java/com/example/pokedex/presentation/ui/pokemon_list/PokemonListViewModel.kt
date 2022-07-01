package com.example.pokedex.presentation.ui.pokemon_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.PokemonDetailDomain
import com.example.pokedex.util.TAG
import com.example.pokedex.domain.use_cases.pokemon_list.GetPokemonsFirstPage
import com.example.pokedex.domain.use_cases.pokemon_list.GetPokemonsNextPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class PokemonListViewModel
@Inject constructor(
    private val getPokemonsFirstPage : GetPokemonsFirstPage,
    private val getPokemonsNextPage: GetPokemonsNextPage,
) : ViewModel()
{

    val page = mutableStateOf(1)

    val pokemons  = mutableStateOf<List<PokemonDetailDomain>>(emptyList())

    val loading = mutableStateOf(false)

    val error = mutableStateOf("")

    init {

        loading.value = true

        viewModelScope.launch {
            getPokemonsFirstPage.execute().collect {

                Log.d("repository", "someone change the loading state")
                loading.value = it.loading

                it.data?.let { data: List<PokemonDetailDomain> ->
                    pokemons.value = data
                }
            }
        }
    }

    fun onTriggerEvent(event: PokemonListEvent) {
        viewModelScope.launch {
            try {
                when(event){
                    is PokemonListEvent.NextPageEvent -> {
                        loading.value = true
                        delay(10)
                        nextPage()
                    }
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

    private suspend fun nextPage(){
        incrementPage()
        getPokemonsNextPage.execute(page = page.value)
    }

    private fun incrementPage(){
        setPage(page.value + 1)
    }

    private fun setPage(page: Int){
        this.page.value = page
    }

}
