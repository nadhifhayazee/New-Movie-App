package com.example.newmovieapp.util

import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.model.MovieResponse
import com.google.gson.Gson

object DataDummyHelper {

    private const val dummiesPopularMovies =
        "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/wwFBRyekDcKXJwP0mImRJjAnudL.jpg\",\"genre_ids\":[27],\"id\":632357,\"original_language\":\"en\",\"original_title\":\"The Unholy\",\"overview\":\"\",\"popularity\":6600.217,\"poster_path\":\"/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg\",\"release_date\":\"2021-03-31\",\"title\":\"The Unholy\",\"video\":false,\"vote_average\":7.1,\"vote_count\":668},{\"adult\":false,\"backdrop_path\":\"/9WlJFhOSCPnaaSmsrv0B4zA8iUb.jpg\",\"genre_ids\":[28,27,53],\"id\":503736,\"original_language\":\"en\",\"original_title\":\"Army of the Dead\",\"overview\":\"\",\"popularity\":4435.269,\"poster_path\":\"/z8CExJekGrEThbpMXAmCFvvgoJR.jpg\",\"release_date\":\"2021-05-14\",\"title\":\"Army of the Dead\",\"video\":false,\"vote_average\":6.6,\"vote_count\":1139},{\"adult\":false,\"backdrop_path\":\"/yyWNPhP1HR4BTLErHcZwIUsMBvA.jpg\",\"genre_ids\":[80,18,9648,53],\"id\":823855,\"original_language\":\"en\",\"original_title\":\"I Am All Girls\",\"overview\":\"\",\"popularity\":2615.127,\"poster_path\":\"/m6bUeV4mczG3z2YXXr5XDKPsQzv.jpg\",\"release_date\":\"2021-05-14\",\"title\":\"I Am All Girls\",\"video\":false,\"vote_average\":7,\"vote_count\":85},{\"adult\":false,\"backdrop_path\":\"/mPBI506o7gITnjoSkcyPneK9s8n.jpg\",\"genre_ids\":[28,14,12],\"id\":460465,\"original_language\":\"en\",\"original_title\":\"Mortal Kombat\",\"overview\":\"\",\"popularity\":2452.21,\"poster_path\":\"/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg\",\"release_date\":\"2021-04-07\",\"title\":\"Mortal Kombat\",\"video\":false,\"vote_average\":7.6,\"vote_count\":2752},{\"adult\":false,\"backdrop_path\":\"/fPGeS6jgdLovQAKunNHX8l0avCy.jpg\",\"genre_ids\":[28,53,10752],\"id\":567189,\"original_language\":\"en\",\"original_title\":\"Tom Clancy's Without Remorse\",\"overview\":\"\",\"popularity\":2246.434,\"poster_path\":\"/oRFMWkP33VY1tpPMRqK4oDmZunI.jpg\",\"release_date\":\"2021-04-29\",\"title\":\"Tom Clancy's Without Remorse\",\"video\":false,\"vote_average\":7.2,\"vote_count\":1056},{\"adult\":false,\"backdrop_path\":\"/iDdpiUnCeXvBmrkBFpL6lKsZt33.jpg\",\"genre_ids\":[53,18,28,9648],\"id\":578701,\"original_language\":\"en\",\"original_title\":\"Those Who Wish Me Dead\",\"overview\":\"\",\"popularity\":2138.005,\"poster_path\":\"/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg\",\"release_date\":\"2021-05-05\",\"title\":\"Those Who Wish Me Dead\",\"video\":false,\"vote_average\":7,\"vote_count\":338}]}"
    private const val dummiesNowPlayingMovies =
        "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/wwFBRyekDcKXJwP0mImRJjAnudL.jpg\",\"genre_ids\":[27],\"id\":632357,\"original_language\":\"en\",\"original_title\":\"The Unholy\",\"overview\":\"\",\"popularity\":6600.217,\"poster_path\":\"/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg\",\"release_date\":\"2021-03-31\",\"title\":\"The Unholy\",\"video\":false,\"vote_average\":7.1,\"vote_count\":668},{\"adult\":false,\"backdrop_path\":\"/9WlJFhOSCPnaaSmsrv0B4zA8iUb.jpg\",\"genre_ids\":[28,27,53],\"id\":503736,\"original_language\":\"en\",\"original_title\":\"Army of the Dead\",\"overview\":\"\",\"popularity\":4435.269,\"poster_path\":\"/z8CExJekGrEThbpMXAmCFvvgoJR.jpg\",\"release_date\":\"2021-05-14\",\"title\":\"Army of the Dead\",\"video\":false,\"vote_average\":6.6,\"vote_count\":1139},{\"adult\":false,\"backdrop_path\":\"/mPBI506o7gITnjoSkcyPneK9s8n.jpg\",\"genre_ids\":[28,14,12],\"id\":460465,\"original_language\":\"en\",\"original_title\":\"Mortal Kombat\",\"overview\":\"\",\"popularity\":2452.21,\"poster_path\":\"/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg\",\"release_date\":\"2021-04-07\",\"title\":\"Mortal Kombat\",\"video\":false,\"vote_average\":7.6,\"vote_count\":2752},{\"adult\":false,\"backdrop_path\":\"/iDdpiUnCeXvBmrkBFpL6lKsZt33.jpg\",\"genre_ids\":[53,18,28,9648],\"id\":578701,\"original_language\":\"en\",\"original_title\":\"Those Who Wish Me Dead\",\"overview\":\"\",\"popularity\":2138.005,\"poster_path\":\"/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg\",\"release_date\":\"2021-05-05\",\"title\":\"Those Who Wish Me Dead\",\"video\":false,\"vote_average\":7,\"vote_count\":338},{\"adult\":false,\"backdrop_path\":\"/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg\",\"genre_ids\":[28,878],\"id\":399566,\"original_language\":\"en\",\"original_title\":\"Godzilla vs. Kong\",\"overview\":\"\",\"popularity\":1932.101,\"poster_path\":\"/lrSjP0NS9urCQlSBaqs0kfmb5bY.jpg\",\"release_date\":\"2021-03-24\",\"title\":\"Godzilla vs. Kong\",\"video\":false,\"vote_average\":8.1,\"vote_count\":5732},{\"adult\":false,\"backdrop_path\":\"/2sbe8qmdYNLQ8wprAXKDNTMbylZ.jpg\",\"genre_ids\":[35,80],\"id\":337404,\"original_language\":\"en\",\"original_title\":\"Cruella\",\"overview\":\"\",\"popularity\":2225.889,\"poster_path\":\"/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg\",\"release_date\":\"2021-05-26\",\"title\":\"Cruella\",\"video\":false,\"vote_average\":8.8,\"vote_count\":880},{\"adult\":false,\"backdrop_path\":\"/3Ef8PWUiP1ehO1ESEroxb736srR.jpg\",\"genre_ids\":[53,28,80],\"id\":808023,\"original_language\":\"en\",\"original_title\":\"The Virtuoso\",\"overview\":\"\",\"popularity\":1923.393,\"poster_path\":\"/vXHzO26mJaOt4VO7ZFiM6No5ScT.jpg\",\"release_date\":\"2021-04-30\",\"title\":\"The Virtuoso\",\"video\":false,\"vote_average\":6.2,\"vote_count\":89},{\"adult\":false,\"backdrop_path\":\"/sBwGOfJtSF6hlXaEgvFfBfeLqMk.jpg\",\"genre_ids\":[28,53,80,35],\"id\":615457,\"original_language\":\"en\",\"original_title\":\"Nobody\",\"overview\":\"Hutch Mansell, seorang ayah di pinggiran kota, suami yang terlantar, tidak ada tetangga - \\\"bukan siapa-siapa\\\". Ketika dua pencuri masuk ke rumahnya pada suatu malam, kemarahan Hutch yang tidak diketahui sejak lama menyala dan mendorongnya ke jalur brutal yang akan mengungkap rahasia gelap yang dia perjuangkan untuk ditinggalkan.\",\"popularity\":1652.858,\"poster_path\":\"/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg\",\"release_date\":\"2021-03-26\",\"title\":\"Nobody\",\"video\":false,\"vote_average\":8.5,\"vote_count\":1740}]}"
    private const val dummiesPopularTvs =
        "{\"page\":1,\"results\":[{\"backdrop_path\":\"/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg\",\"first_air_date\":\"2016-01-25\",\"genre_ids\":[80,10765],\"id\":63174,\"name\":\"Lucifer\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Lucifer\",\"overview\":\"Bosan dan tidak bahagia sebagai Penguasa Neraka, Lucifer Morningstar meninggalkan tahtanya dan pensiun ke Los Angeles, di mana ia telah bekerja sama dengan detektif LAPD Chloe Decker untuk menjatuhkan penjahat. Tapi semakin lama dia menjauh dari dunia bawah, semakin besar ancaman bahwa yang terburuk dari umat manusia dapat melarikan diri.\",\"popularity\":2045.499,\"poster_path\":\"/vkGnVBSNpayJ1oCfyVaMvhrim95.jpg\",\"vote_average\":8.5,\"vote_count\":8878},{\"backdrop_path\":\"/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg\",\"first_air_date\":\"2014-10-07\",\"genre_ids\":[18,10765],\"id\":60735,\"name\":\"The Flash\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Flash\",\"overview\":\"Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya \\\"manusia meta\\\" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.\",\"popularity\":1044.283,\"poster_path\":\"/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg\",\"vote_average\":7.7,\"vote_count\":7713},{\"backdrop_path\":\"/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg\",\"first_air_date\":\"2017-09-25\",\"genre_ids\":[18],\"id\":71712,\"name\":\"The Good Doctor\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Good Doctor\",\"overview\":\"\",\"popularity\":944.21,\"poster_path\":\"/z1K4mJwISETia59rrnMdXxzoSrZ.jpg\",\"vote_average\":8.6,\"vote_count\":8507},{\"backdrop_path\":\"/dYvIUzdh6TUv4IFRq8UBkX7bNNu.jpg\",\"first_air_date\":\"2021-03-24\",\"genre_ids\":[18,80,9648],\"id\":120168,\"name\":\"¿Quién mató a Sara?\",\"origin_country\":[\"MX\"],\"original_language\":\"es\",\"original_name\":\"¿Quién mató a Sara?\",\"overview\":\"\",\"popularity\":880.596,\"poster_path\":\"/dXIXXFQmnozQl2ZJ7rM4oMI2zjy.jpg\",\"vote_average\":7.8,\"vote_count\":742},{\"backdrop_path\":\"/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg\",\"first_air_date\":\"2021-03-19\",\"genre_ids\":[10765,10759,18,10768],\"id\":88396,\"name\":\"The Falcon and the Winter Soldier\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Falcon and the Winter Soldier\",\"overview\":\"\",\"popularity\":869.417,\"poster_path\":\"/6kbAMLteGO8yyewYau6bJ683sw7.jpg\",\"vote_average\":7.9,\"vote_count\":5676}]}"
    private const val dummiesNowPlayingTv =
        "{\"page\":1,\"results\":[{\"backdrop_path\":\"/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg\",\"first_air_date\":\"2017-09-25\",\"genre_ids\":[18],\"id\":71712,\"name\":\"The Good Doctor\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Good Doctor\",\"overview\":\"\",\"popularity\":944.21,\"poster_path\":\"/z1K4mJwISETia59rrnMdXxzoSrZ.jpg\",\"vote_average\":8.6,\"vote_count\":8507},{\"backdrop_path\":\"/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg\",\"first_air_date\":\"2005-03-27\",\"genre_ids\":[18],\"id\":1416,\"name\":\"Grey's Anatomy\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Grey's Anatomy\",\"overview\":\"Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.\",\"popularity\":780.378,\"poster_path\":\"/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg\",\"vote_average\":8.2,\"vote_count\":6134},{\"backdrop_path\":\"/pPKiIJEEcV0E1hpVcWRXyp73ZpX.jpg\",\"first_air_date\":\"2021-02-23\",\"genre_ids\":[10759,10765,18],\"id\":95057,\"name\":\"Superman & Lois\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Superman & Lois\",\"overview\":\"\",\"popularity\":649.356,\"poster_path\":\"/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg\",\"vote_average\":8.3,\"vote_count\":876},{\"backdrop_path\":\"/sjxtIUCWR74yPPcZFfTsToepfWm.jpg\",\"first_air_date\":\"2021-05-04\",\"genre_ids\":[10765,10759,16],\"id\":105971,\"name\":\"The Bad Batch\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Bad Batch\",\"overview\":\"\",\"popularity\":502.134,\"poster_path\":\"/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg\",\"vote_average\":8.8,\"vote_count\":230},{\"backdrop_path\":\"/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg\",\"first_air_date\":\"2015-08-23\",\"genre_ids\":[10759,18],\"id\":62286,\"name\":\"Fear the Walking Dead\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Fear the Walking Dead\",\"overview\":\"Seperti apa dunia saat berubah menjadi kiamat mengerikan yang digambarkan dalam \\\"The Walking Dead\\\"? Spin-off ini ditetapkan di Los Angeles, mengikuti karakter baru saat mereka menghadapi awal akhir dunia, akan menjawab pertanyaan itu.\",\"popularity\":458.826,\"poster_path\":\"/aOdTWn8dXlS0tA5xl0ZBr8Ws15R.jpg\",\"vote_average\":7.6,\"vote_count\":3574},{\"backdrop_path\":\"/hNiGqLsiD30C194lci7VYDmciHD.jpg\",\"first_air_date\":\"2017-04-26\",\"genre_ids\":[10765,18],\"id\":69478,\"name\":\"The Handmaid's Tale\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Handmaid's Tale\",\"overview\":\"\",\"popularity\":428.921,\"poster_path\":\"/1ryCwZaZFAlG0c1w8XiMHeAxxYy.jpg\",\"vote_average\":8.2,\"vote_count\":1390},{\"backdrop_path\":\"/18ybGiTcKVnXCYCAmVBIIy8CNhx.jpg\",\"first_air_date\":\"2019-10-18\",\"genre_ids\":[99],\"id\":94667,\"name\":\"De viaje con los Derbez\",\"origin_country\":[\"MX\"],\"original_language\":\"es\",\"original_name\":\"De viaje con los Derbez\",\"overview\":\"\",\"popularity\":356.835,\"poster_path\":\"/o1lAdiCYmCuDb25wyBCJQMeUhVA.jpg\",\"vote_average\":7.5,\"vote_count\":915},{\"backdrop_path\":\"/cD9PxbrdWYgL7MUpD9eOYuiSe2P.jpg\",\"first_air_date\":\"1999-09-20\",\"genre_ids\":[80,18],\"id\":2734,\"name\":\"Law & Order: Special Victims Unit\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Law & Order: Special Victims Unit\",\"overview\":\"Dalam sistem peradilan pidana, pelanggaran berbasis seksual dianggap sangat kejam. Di New York City, detektif yang berdedikasi yang menyelidiki kejahatan keji ini adalah anggota pasukan elit yang dikenal sebagai Unit Korban Khusus. Ini adalah kisah mereka.\",\"popularity\":338.099,\"poster_path\":\"/ydSvHgksuB8Ix0V05QEZBKrnIcg.jpg\",\"vote_average\":7.8,\"vote_count\":2436}]}"

    private const val dummiesMovie =
        "{\"adult\":false,\"backdrop_path\":\"/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg\",\"belongs_to_collection\":null,\"budget\":200000000,\"genres\":[{\"id\":35,\"name\":\"Komedi\"},{\"id\":80,\"name\":\"Kejahatan\"}],\"homepage\":\"https://movies.disney.com/cruella\",\"id\":337404,\"imdb_id\":\"tt3228774\",\"original_language\":\"en\",\"original_title\":\"Cruella\",\"overview\":\"\",\"popularity\":6821.402,\"poster_path\":\"/A0knvX7rlwTyZSKj8H5NiARb45.jpg\",\"production_companies\":[{\"id\":2,\"logo_path\":\"/wdrCwmRnLFJhEoH8GSfymY85KHT.png\",\"name\":\"Walt Disney Pictures\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"release_date\":\"2021-05-26\",\"revenue\":46586903,\"runtime\":134,\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"\",\"title\":\"Cruella\",\"video\":false,\"vote_average\":8.7,\"vote_count\":1600}"
    private const val dummiesTv =
        "{\"backdrop_path\":\"/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg\",\"created_by\":[{\"id\":1222585,\"credit_id\":\"55fdc50ec3a368132a001852\",\"name\":\"Tom Kapinos\",\"gender\":2,\"profile_path\":\"/ol7GfeO0OIDCWGYzlg1LDLmwluO.jpg\"}],\"episode_run_time\":[45],\"first_air_date\":\"2016-01-25\",\"genres\":[{\"id\":80,\"name\":\"Kejahatan\"},{\"id\":10765,\"name\":\"Sci-fi & Fantasy\"}],\"homepage\":\"https://www.netflix.com/title/80057918\",\"id\":63174,\"in_production\":true,\"languages\":[\"en\"],\"last_air_date\":\"2021-05-28\",\"last_episode_to_air\":{\"air_date\":\"2021-05-28\",\"episode_number\":16,\"id\":2856945,\"name\":\"\",\"overview\":\"\",\"production_code\":\"\",\"season_number\":5,\"still_path\":\"/cYY0U8DAkCRAWO6rnIcZ2gW17Fz.jpg\",\"vote_average\":10.0,\"vote_count\":1},\"name\":\"Lucifer\",\"next_episode_to_air\":null,\"networks\":[{\"name\":\"FOX\",\"id\":19,\"logo_path\":\"/1DSpHrWyOORkL9N2QHX7Adt31mQ.png\",\"origin_country\":\"US\"},{\"name\":\"Netflix\",\"id\":213,\"logo_path\":\"/wwemzKWzjKYJFfCeiB57q3r4Bcm.png\",\"origin_country\":\"\"}],\"number_of_episodes\":93,\"number_of_seasons\":6,\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Lucifer\",\"overview\":\"Bosan dan tidak bahagia sebagai Penguasa Neraka, Lucifer Morningstar meninggalkan tahtanya dan pensiun ke Los Angeles, di mana ia telah bekerja sama dengan detektif LAPD Chloe Decker untuk menjatuhkan penjahat. Tapi semakin lama dia menjauh dari dunia bawah, semakin besar ancaman bahwa yang terburuk dari umat manusia dapat melarikan diri.\",\"popularity\":1642.45,\"poster_path\":\"/vkGnVBSNpayJ1oCfyVaMvhrim95.jpg\",\"production_companies\":[{\"id\":43346,\"logo_path\":null,\"name\":\"Fox Productions\",\"origin_country\":\"US\"},{\"id\":1957,\"logo_path\":\"/3T19XSr6yqaLNK8uJWFImPgRax0.png\",\"name\":\"Warner Bros. Television\",\"origin_country\":\"US\"},{\"id\":57542,\"logo_path\":null,\"name\":\"Aggressive Mediocrity\",\"origin_country\":\"US\"},{\"id\":9993,\"logo_path\":\"/2Tc1P3Ac8M479naPp1kYT3izLS5.png\",\"name\":\"DC Entertainment\",\"origin_country\":\"US\"},{\"id\":40041,\"logo_path\":\"/oP8TmVSh9DCP1yhR2yvjnKfMgbg.png\",\"name\":\"Jerry Bruckheimer Television\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"seasons\":[{\"air_date\":\"2015-07-10\",\"episode_count\":4,\"id\":70781,\"name\":\"Specials\",\"overview\":\"\",\"poster_path\":\"/bQ5FupU7DFTbx9pSgPsEZQwyZKj.jpg\",\"season_number\":0},{\"air_date\":\"2016-01-25\",\"episode_count\":13,\"id\":68415,\"name\":\"Musim ke 1\",\"overview\":\"\",\"poster_path\":\"/9qvNXKYqZEsYn3g3yn5tXQe0ceB.jpg\",\"season_number\":1},{\"air_date\":\"2016-09-19\",\"episode_count\":18,\"id\":78529,\"name\":\"Musim ke 2\",\"overview\":\"\",\"poster_path\":\"/fTQzbse8HKh0z6UJbMUumdbZ8PX.jpg\",\"season_number\":2},{\"air_date\":\"2017-10-02\",\"episode_count\":26,\"id\":91441,\"name\":\"Musim ke 3\",\"overview\":\"\",\"poster_path\":\"/4mKbrTqGg1daz3pDUgqd9ZSdZRt.jpg\",\"season_number\":3},{\"air_date\":\"2019-05-08\",\"episode_count\":10,\"id\":117593,\"name\":\"Musim ke 4\",\"overview\":\"\",\"poster_path\":\"/k9sLJE5geAmOCXOCbKnhSNND60J.jpg\",\"season_number\":4},{\"air_date\":\"2020-08-21\",\"episode_count\":16,\"id\":152759,\"name\":\"Musim ke 5\",\"overview\":\"\",\"poster_path\":\"/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg\",\"season_number\":5},{\"air_date\":null,\"episode_count\":10,\"id\":192616,\"name\":\"Musim ke 6\",\"overview\":\"\",\"poster_path\":null,\"season_number\":6}],\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Returning Series\",\"tagline\":\"\",\"type\":\"Scripted\",\"vote_average\":8.5,\"vote_count\":9021}"

    fun getPopularMovieDummies(): ArrayList<Movie> {
        val movieResponse = Gson().fromJson(dummiesPopularMovies, MovieResponse::class.java)
        return movieResponse.results ?: arrayListOf()
    }

    fun getPlayingMovieDummies(): ArrayList<Movie> {
        val movieResponse = Gson().fromJson(dummiesNowPlayingMovies, MovieResponse::class.java)
        return movieResponse.results ?: arrayListOf()
    }

    fun getDummyMovie(): Movie {
        return Gson().fromJson(dummiesMovie, Movie::class.java)
    }

    fun getPopularTvDummies(): ArrayList<Movie> {
        val movieResponse = Gson().fromJson(dummiesPopularTvs, MovieResponse::class.java)
        return movieResponse.results ?: arrayListOf()
    }

    fun getPlayingTvDummies(): ArrayList<Movie> {
        val movieResponse = Gson().fromJson(dummiesNowPlayingTv, MovieResponse::class.java)
        return movieResponse.results ?: arrayListOf()
    }

    fun getDummyTv(): Movie {
        return Gson().fromJson(dummiesTv, Movie::class.java)
    }

    fun dummyMovieEntity(): MovieEntity {
        val dummy = getDummyMovie()
        val entity = MovieEntity()
        entity.movie_id = dummy.id
        entity.data = Gson().toJson(dummy)
        return entity
    }

    fun dummyTvEntity(): TvEntity {
        val dummy = getDummyTv()
        val entity = TvEntity()
        entity.tv_id = dummy.id
        entity.data = Gson().toJson(dummy)
        return entity
    }



    fun getDummyFavoriteMovie(): List<MovieEntity> {
        val gson = Gson()
        val movieResponse = gson.fromJson(dummiesPopularMovies, MovieResponse::class.java)
        val movies = movieResponse.results
        val favorites = arrayListOf<MovieEntity>()
        movies?.forEachIndexed { index, movie ->
            val movieEntity = MovieEntity()
            movieEntity.id = index + 1
            movieEntity.movie_id = movie.id
            movieEntity.data = gson.toJson(movie)
            favorites.add(movieEntity)
        }
        return favorites
    }

    fun getDummyFavoriteTv(): List<TvEntity> {
        val gson = Gson()
        val movieResponse = gson.fromJson(dummiesPopularTvs, MovieResponse::class.java)
        val movies = movieResponse.results
        val favorites = arrayListOf<TvEntity>()
        movies?.forEachIndexed { index, movie ->
            val tvEntity = TvEntity()
            tvEntity.id = index + 1
            tvEntity.tv_id = movie.id
            tvEntity.data = gson.toJson(movie)
            favorites.add(tvEntity)
        }
        return favorites
    }
}