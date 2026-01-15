package com.example.watchly.data.model

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