package by.bsuir.ief172303.kotova_marina.presentation.states

import by.bsuir.ief172303.kotova_marina.data.database.FavoriteMovieEntity

data class FavoriteMovieViewState(
    val favoriteMovies: List<FavoriteMovieEntity>
)