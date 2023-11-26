package by.bsuir.ief172303.kotova_marina.repository.database
import androidx.room.TypeConverter
import by.bsuir.ief172303.kotova_marina.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMovie(movie: Movie): String {
        return gson.toJson(movie)
    }

    @TypeConverter
    fun toMovie(movieString: String): Movie {
        val type = object : TypeToken<Movie>() {}.type
        return gson.fromJson(movieString, type)
    }
}