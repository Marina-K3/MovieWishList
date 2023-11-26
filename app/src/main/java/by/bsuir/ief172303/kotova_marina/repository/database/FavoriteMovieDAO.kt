package by.bsuir.ief172303.kotova_marina.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movies")
     fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)
    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)
}