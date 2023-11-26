package by.bsuir.ief172303.kotova_marina.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.ief172303.kotova_marina.model.Movie
import by.bsuir.ief172303.kotova_marina.repository.MovieRepository
import by.bsuir.ief172303.kotova_marina.repository.database.FavoriteMovieDao
import by.bsuir.ief172303.kotova_marina.repository.database.FavoriteMovieEntity
import kotlinx.coroutines.launch



class MovieViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository, // Инжектируйте MovieRepository
    private val favoriteMovieDao: FavoriteMovieDao // Инжектируйте FavoriteMovieDao
) : ViewModel()
{

    // Используйте конструкторы для внедрения зависимостей

    private val _favoriteMovies = MutableLiveData<List<FavoriteMovieEntity>>()
    val favoriteMovies: LiveData<List<FavoriteMovieEntity>> get() = _favoriteMovies
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        if (savedStateHandle.contains(MOVIES_KEY)) {
            _movies.value = savedStateHandle.get<List<Movie>>(MOVIES_KEY)
        } else {
            loadMovies()
            loadFavoriteMovies()
        }
    }

    private fun loadFavoriteMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            favoriteMovieDao.getAllFavoriteMovies().collect {
                _favoriteMovies.value = it
                _isLoading.value = false
            }
        }
    }

    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            val favoriteMovie = FavoriteMovieEntity(0, movie, null, null)
            favoriteMovieDao.insertFavoriteMovie(favoriteMovie)
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val topRatedMovies = movieRepository.getTopRatedMovies()
            _movies.value = topRatedMovies
            savedStateHandle.set(MOVIES_KEY, topRatedMovies)
            _isLoading.value = false
        }
    }

    fun removeFromFavorites(favoriteMovie: FavoriteMovieEntity) {
        viewModelScope.launch {
            favoriteMovieDao.deleteFavoriteMovie(favoriteMovie)
        }
    }

    fun submitReview(favoriteMovie: FavoriteMovieEntity, comment: String, rating: Float) {
        viewModelScope.launch {
            val updatedFavoriteMovie = favoriteMovie.copy(comment = comment, rating = rating)
            favoriteMovieDao.insertFavoriteMovie(updatedFavoriteMovie)
        }
    }

    companion object {
        private const val MOVIES_KEY = "movies"
    }
}