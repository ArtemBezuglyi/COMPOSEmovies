package ru.artbez.composemovies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.artbez.composemovies.MainViewModel
import ru.artbez.composemovies.screens.DetailsScreen
import ru.artbez.composemovies.screens.MainScreen
import ru.artbez.composemovies.screens.SplashScreen
import ru.artbez.composemovies.utils.Const.Screens.DETAILS_SCREEN
import ru.artbez.composemovies.utils.Const.Screens.MAIN_SCREEN
import ru.artbez.composemovies.utils.Const.Screens.SPLASH_SCREEN

sealed class Screens(val route: String) {
    object Splash: Screens(route = SPLASH_SCREEN)
    object Main: Screens(route = MAIN_SCREEN)
    object Details: Screens(route = DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}") { backStackEntry ->
            DetailsScreen(
                navController = navController,
                viewModel = viewModel,
                itemId = backStackEntry.arguments?.getString("Id") ?: "1"
            )
        }
    }

}