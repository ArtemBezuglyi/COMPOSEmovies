package ru.artbez.composemovies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ru.artbez.composemovies.MainViewModel
import ru.artbez.composemovies.data.models.Movies
import ru.artbez.composemovies.navigation.Screens
import ru.artbez.composemovies.ui.theme.MyBlue
import ru.artbez.composemovies.ui.theme.MyGreen
import ru.artbez.composemovies.ui.theme.MyOrange
import ru.artbez.composemovies.ui.theme.MyPink

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value
    
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(20.dp)
        ) {
            items(allMovies.take(10)) { item ->
                MovieItem(item = item, navController)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItem(item: Movies, navController: NavController){

    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable {
                navController.navigate(Screens.Details.route + "/${item.id}")
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ){
            Image(
                modifier = Modifier.size(128.dp),
                painter = rememberImagePainter(item.image.medium),
                contentDescription = "item image"
            )
            Column {
                Text(
                    text = item.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = MyBlue
                )
                Text(
                    text = "${item.rating}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyPink
                )
                Row {
                    Text(
                        text = "Genre:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MyGreen
                    )
                    item.genres.take(3).forEach { Text(text = " $it") }
                }
                Text(
                    text = item.premiered,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyOrange
                )


            }


        }
    }
}