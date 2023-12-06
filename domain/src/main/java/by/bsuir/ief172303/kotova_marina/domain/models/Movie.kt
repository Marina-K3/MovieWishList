package by.bsuir.ief172303.kotova_marina.domain.models

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val year: String,
    val posterURL: String,
    val rating: Double,
    val directors: List<String>
)