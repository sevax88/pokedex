package com.example.consultivescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.consultivescreen.presentation.navigation.Screen
import com.example.consultivescreen.presentation.ui.pokemon_list.PokemonListScreen
import com.example.consultivescreen.presentation.ui.pokemon_list.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
               val navController = rememberNavController()
               NavHost(navController = navController, startDestination = Screen.PokemonList.route) {
                    composable(route = Screen.PokemonList.route){ navBackStackEntry ->
                        val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                        val viewModel = viewModel<PokemonListViewModel>(key= "PokemonListViewModel", factory = factory)
                        PokemonListScreen(viewModel = viewModel)
                    }
               }

        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ConsultiveScreenTheme {
//        val pokemon = Pokemon(name = "Bulbasaur", type = "Plant", image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
//        PokemonView(pokemon = pokemon)
//    }
//}