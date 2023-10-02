package by.bsuir.ief172303.kotova_marina.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.ief172303.kotova_marina.model.Movie
import by.bsuir.ief172303.kotova_marina.repository.MovieRepository
import kotlinx.coroutines.launch
class MovieViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _favoriteMovies = MutableLiveData<List<Movie>>()
    val favoriteMovies: LiveData<List<Movie>> get() = _favoriteMovies

    init {
        if (savedStateHandle.contains(MOVIES_KEY)) {
            _movies.value = savedStateHandle.get<List<Movie>>(MOVIES_KEY)
        } else {
            loadMovies()
        }

        if (savedStateHandle.contains(FAVORITE_MOVIES_KEY)) {
            _favoriteMovies.value = savedStateHandle.get<List<Movie>>(FAVORITE_MOVIES_KEY)
        }
    }

    fun addToFavorites(movie: Movie) {
        val currentFavorites = _favoriteMovies.value.orEmpty().toMutableList()
        currentFavorites.add(movie)
        _favoriteMovies.value = currentFavorites
        savedStateHandle.set(FAVORITE_MOVIES_KEY, currentFavorites)
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val topRatedMovies = movieRepository.getTopRatedMovies()
            _movies.value = topRatedMovies
            savedStateHandle.set(MOVIES_KEY, topRatedMovies)
        }
    }



    companion object {
        private const val MOVIES_KEY = "movies"
        private const val FAVORITE_MOVIES_KEY = "favorite_movies"
    }
}