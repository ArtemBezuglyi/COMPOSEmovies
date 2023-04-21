package ru.artbez.composemovies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ru.artbez.composemovies.MainViewModel
import ru.artbez.composemovies.ui.theme.MyBlue
import ru.artbez.composemovies.ui.theme.MyGreen
import ru.artbez.composemovies.ui.theme.MyOrange
import ru.artbez.composemovies.ui.theme.MyPink
import ru.artbez.composemovies.utils.HtmlText

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {

    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt()}

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        LazyColumn{
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberImagePainter(currentItem?.image?.medium),
                        contentDescription = "details image",
                        modifier = Modifier.size(500.dp)
                    )

                    Text(
                        text = currentItem?.name ?: "Unknown",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "${currentItem?.rating}",
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
                        currentItem?.genres?.take(3)?.forEach {
                            Text(
                                text = " [$it]",
                                fontSize = 18.sp
                            )
                        }
                    }
                    Text(
                        text = "${currentItem?.premiered}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MyOrange
                    )
                    HtmlText(
                        html = "${currentItem?.summary}",
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "${currentItem?.summary}",
                        fontSize = 18.sp,
                        color = MyBlue
                    )
                }
            }
        }

    }
}