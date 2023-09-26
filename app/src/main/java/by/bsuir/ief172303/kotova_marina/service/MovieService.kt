package by.bsuir.ief172303.kotova_marina.service

import by.bsuir.ief172303.kotova_marina.models.Movie
import retrofit2.Call
import retrofit2.http.GET

data class MovieResponse(
    val movies: List<Movie>
)

interface MovieService {
    @GET("movies")
    fun getMovies(): Call<MovieResponse>
}