package ru.artbez.composemovies.utils

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

class Const {

    object Screens {
        const val SPLASH_SCREEN = "Splash screen"
        const val MAIN_SCREEN = "Main screen"
        const val DETAILS_SCREEN = "Details screen"
    }

}

const val BASE_URL = "https://api.tvmaze.com/"

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context)
        },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)}
    )
}