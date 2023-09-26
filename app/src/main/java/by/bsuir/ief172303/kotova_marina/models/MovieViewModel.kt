package by.bsuir.ief172303.kotova_marina.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val directors: List<String>,
    val rating: Float
)
class MovieViewModel : ViewModel() {
        private val _movies = MutableLiveData<List<Movie>>()
        val movies: LiveData<List<Movie>> get() = _movies

        fun addMovie(movie: Movie) {
            val currentList = _movies.value.orEmpty().toMutableList()
            currentList.add(movie)
            _movies.value = currentList
        }

        fun deleteMovie(movie: Movie) {
            val currentList = _movies.value.orEmpty().toMutableList()
            currentList.remove(movie)
            _movies.value = currentList
        }

        fun updateMovie(movie: Movie) {
            val currentList = _movies.value.orEmpty().toMutableList()
            val index = currentList.indexOfFirst { it.id == movie.id }
            if (index != -1) {
                currentList[index] = movie
                _movies.value = currentList
            }
        }
    }
