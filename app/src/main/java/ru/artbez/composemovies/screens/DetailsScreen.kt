package ru.artbez.composemovies.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.artbez.composemovies.MainViewModel

@Composable
fun DetailsScreen(navController: NavController, viewModel: MainViewModel, itemId: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Details screen - item id: $itemId")
    }
}