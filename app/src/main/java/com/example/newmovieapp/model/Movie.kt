package com.example.newmovieapp.model

data class Movie(
    var id: Int = 0,
    var original_title: String? = null,
    var release_date: String? = null,
    var genres: ArrayList<Genre?>? = null,
    var genre_ids: ArrayList<Int?>? = null,
    var vote_average: Float = 0f,
    var overview: String? = null,
    var poster_path: String? = null,
    var backdrop_path: String? = null,
    var first_air_date: String? = null,
    var original_name: String? = null,
    var name: String? = null,
    var runtime: Int = 0,
    val title: String? = null,
    val episode_run_time: ArrayList<Int>? = null


) {
    fun getGenres(): String {
        if (genres?.isNotEmpty() == true) {
            var genre = ""
            genres?.forEachIndexed { index, it ->
                genre += it?.name
                if (index < (genres?.size ?: 0) - 1) {
                    genre += ", "
                }
            }
            return genre
        }
        return "-"
    }

}