package academy.bangkit.digitalpolice.core.api

import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.DeviceToken
import academy.bangkit.digitalpolice.core.data.source.remote.models.DeviceTokenResponse
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("city")
    fun getCities(): Call<List<City>>

    @GET("history")
    fun getHistories(): Call<List<History>>

    @GET("history_today")
    fun getHistoriesToday(): Call<List<History>>

    @GET("history/by_city/{city_id}")
    fun getHistoryByCity(@Path("city_id") city_id: Int): Call<List<History>>

    @GET("history/{id}")
    fun getHistory(@Path("id") id: Int): Call<History>

    @POST("/token/")
    fun savePost(@Body token: DeviceToken): Call<DeviceTokenResponse>



}