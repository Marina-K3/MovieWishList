package by.bsuir.ief172303.kotova_marina.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsuir.ief172303.kotova_marina.domain.models.Movie

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie") val movie: Movie,
    @ColumnInfo(name = "rating") val rating: Float?,
    @ColumnInfo(name = "comment") val comment: String?
)