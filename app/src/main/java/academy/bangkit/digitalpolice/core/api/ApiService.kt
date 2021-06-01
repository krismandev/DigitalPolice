package academy.bangkit.digitalpolice.core.api

import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @GET("history")
//    fun getAllHistories() : Call<MovieResponse>

//    @GET("history/{id}")
//    fun getDetailHistory(@Path("id") id:Int): Call<>

    @GET("city")
    fun getCities(): Call<List<City>>

    @GET("history")
    fun getHistories(): Call<List<History>>

    @GET("history/by_city/{city_id}")
    fun getHistoryByCity(@Path("city_id") city_id: Int): Call<List<History>>


//    @GET("tv/popular?api_key=$API_KEY")
//    fun getTvShows(
//        @Query("page") page: Int
//    ) : Call<TvShowResponse>
//
//    @GET("movie/{id}?api_key=$API_KEY")
//    fun getMovie(@Path("id") id: Int): Call<Movie>
//
//    @GET("tv/{id}?api_key=$API_KEY")
//    fun getTvShow(@Path("id") id: Int): Call<TvShow>

}