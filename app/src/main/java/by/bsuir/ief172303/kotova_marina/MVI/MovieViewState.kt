package by.bsuir.ief172303.kotova_marina.MVI

import by.bsuir.ief172303.kotova_marina.model.Movie

data class MovieViewState(
    val movie: Movie?,
    val isLoading: Boolean
)