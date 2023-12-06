package by.bsuir.ief172303.kotova_marina.domain.models


data class FavoriteMovie(
    val movie: Movie,
    val rating: Float?,
    val comment: String?
)