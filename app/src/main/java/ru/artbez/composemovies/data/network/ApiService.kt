package ru.artbez.composemovies.data.network

import retrofit2.Response
import retrofit2.http.GET
import ru.artbez.composemovies.data.models.Movies

interface ApiService {

    @GET("shows/")
    suspend fun getAllMovies() : Response<List<Movies>>
}