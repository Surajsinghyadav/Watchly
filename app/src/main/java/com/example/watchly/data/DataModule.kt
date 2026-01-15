package com.example.watchly.data

data class DataModule(
    val page: Int,
    val titles: List<Title>,
    val total_pages: Int,
    val total_results: Int
)