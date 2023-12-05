package by.bsuir.ief172303.kotova_marina.states

import by.bsuir.ief172303.kotova_marina.database.FavoriteMovieEntity
import by.bsuir.ief172303.kotova_marina.models.FavoriteMovie

data class FavoriteMovieViewState(
    val favoriteMovies: List<FavoriteMovieEntity>
)