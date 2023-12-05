package by.bsuir.ief172303.kotova_marina.intent

import by.bsuir.ief172303.kotova_marina.database.FavoriteMovieEntity


sealed class FavoriteMovieIntent {
    object LoadFavoriteMovies : FavoriteMovieIntent()
    data class RemoveFromFavorites(val favoriteMovie: FavoriteMovieEntity) : FavoriteMovieIntent()
    data class SubmitReview(val favoriteMovie: FavoriteMovieEntity, val comment: String, val rating: Float) : FavoriteMovieIntent()
}