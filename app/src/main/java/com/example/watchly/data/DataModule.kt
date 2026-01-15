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
    val plot_overview: String?,
    val runtime_minutes: Int?,
    val release_date: String?,
    val genre_names: List<String>?,
    val poster: String?,
    val backdrop: String?
)



