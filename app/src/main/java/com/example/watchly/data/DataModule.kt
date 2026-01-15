package com.example.watchly.data

data class DataModule(
    val page: Int,
    val titles: List<Title>,
    val total_pages: Int,
    val total_results: Int
)


data class TitleDetailsResponse(
    val id: Int,
    val title: String?,
    val original_title: String?,
    val plot_overview: String?,
    val user_rating: String?,
    val runtime_minutes: Int?,
    val release_date: String?,
    val genre_names: List<String>?,
    val poster: String?,
    val backdrop: String?
)


data class Title(
    val id: Int,
    val imdb_id: String,
    val title: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val type: String,
    val year: Int
)




