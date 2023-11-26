package by.bsuir.ief172303.kotova_marina.repository

import android.util.Log
import by.bsuir.ief172303.kotova_marina.model.Movie
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.coroutines.delay
import kotlin.collections.filter


class MovieRepository {

    private suspend fun delayBetweenRequests() {
        delay(20) // Задержка 20 миллисекунд
    }

    private val httpClient = HttpClient()
    private val gson = Gson()

    suspend fun getTopRatedMovies(): List<Movie> {
        val response = httpClient.get(
            "https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_250_BEST_FILMS&page=1"
        ) {
            headers {
                append("X-API-KEY", "70565c00-50c5-48e9-9468-2ac4b084103e")
            }
        }
        val json = response.body<String>()
        Log.d("MARINA", "рейтинговые фильмы - $json")
        val movieList = gson.fromJson(json, Map::class.java)["films"] as List<Map<String, Any>>
        val top20Films = movieList.take(20)
        val movies = mutableListOf<Movie>()

        for (film in top20Films) {
            val filmId = (film["filmId"] as Double).toInt()
            val movieDetails = getMovieDetails(filmId)
            val directors = getMovieStaff(filmId)
            Log.d("MARINA", "режиссеры - $directors")
            Log.d("MARINA", "детали - $movieDetails")
            val movie = Movie(
                id = filmId,
                name = film["nameRu"] as String,
                description = movieDetails.description,
                year = film["year"] as String,
                posterURL = film["posterUrl"] as String,
                rating = (film["rating"] as String).toDouble(),
                directors = directors
            )

            movies.add(movie)
        }
        Log.d("MARINA", "фильмы - $movies")

        return movies
    }

    suspend fun getMovieDetails(movieId: Int): Movie {
        delayBetweenRequests()
        val response = httpClient.get(
            "https://kinopoiskapiunofficial.tech/api/v2.2/films/$movieId"
        ) {
            headers {
                append("X-API-KEY", "70565c00-50c5-48e9-9468-2ac4b084103e")
            }
        }
        val json = response.body<String>()
        Log.d("MARINA", "ответ 2 - $json")
        val movieDetails = gson.fromJson(json, Map::class.java)
        Log.d("MARINA", "ответ в ф2 - $movieDetails")

        val description = movieDetails["description"] as String
        Log.d("MARINA", "описание - $movieDetails")


        return Movie(0, "", description, "", "", 0.0, emptyList())
    }


    suspend fun getMovieStaff(movieId: Int): List<String> {

        delayBetweenRequests()

        val response = httpClient.get(
            "https://kinopoiskapiunofficial.tech/api/v1/staff?filmId=$movieId"
        ) {
            headers {
                append("X-API-KEY", "70565c00-50c5-48e9-9468-2ac4b084103e")
            }
        }
        val json = response.body<String>()
        Log.d("MARINA", "стафф - $json")
        val staffList = gson.fromJson(json, List::class.java) as List<Map<String, Any>>
        Log.d("MARINA", "персонал - $staffList")

        val directors = staffList
            .filter { staff -> staff["professionKey"] == "DIRECTOR" }
            .map { staff -> staff["nameRu"] as String }

        return directors
    }
}