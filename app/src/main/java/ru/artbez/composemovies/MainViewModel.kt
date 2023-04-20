package ru.artbez.composemovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.artbez.composemovies.data.models.Movies
import ru.artbez.composemovies.data.network.ApiRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val a11Movies = MutableLiveData<List<Movies>>()
    val allMovies: LiveData<List<Movies>>
        get() = a11Movies

    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies().let {
                if (it.isSuccessful) {
                    a11Movies.postValue(it.body())
                } else {
                    Log.d("checkData", "Failed to load movies: ${it.errorBody()}")
                }
            }
        }
    }

}