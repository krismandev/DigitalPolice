package academy.bangkit.digitalpolice.core.data.source.remote

import academy.bangkit.digitalpolice.core.api.ApiConfig
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private const val TAG = "RemoteDataSource"

        fun getInstance(): RemoteDataSource {
            return RemoteDataSource()
        }
    }

    fun getCities(): LiveData<List<City>>{
        val cities: MutableLiveData<List<City>> = MutableLiveData()
        val client = ApiConfig.getApiService().getCities()

        client.enqueue(object : Callback<List<City>>{
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful){
                    cities.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return cities

    }
}