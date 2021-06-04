package academy.bangkit.digitalpolice.core.data.source.remote

import academy.bangkit.digitalpolice.core.api.ApiConfig
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.DeviceToken
import academy.bangkit.digitalpolice.core.data.source.remote.models.DeviceTokenResponse
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
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

    fun getHistories(): LiveData<List<History>>{
        val histories: MutableLiveData<List<History>> = MutableLiveData()
        val client = ApiConfig.getApiService().getHistories()
        client.enqueue(object : Callback<List<History>>{
            override fun onResponse(call: Call<List<History>>, response: Response<List<History>>) {
                if (response.isSuccessful){
                    histories.value = response.body()
                }
                Log.d(TAG,response.body().toString())
            }
            override fun onFailure(call: Call<List<History>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return histories
    }

    fun getHistoriesToday(): LiveData<List<History>>{
        val histories: MutableLiveData<List<History>> = MutableLiveData()
        val client = ApiConfig.getApiService().getHistoriesToday()
        client.enqueue(object : Callback<List<History>>{
            override fun onResponse(call: Call<List<History>>, response: Response<List<History>>) {
                if (response.isSuccessful){
                    histories.value = response.body()
                }
                Log.d(TAG,response.body().toString())
            }
            override fun onFailure(call: Call<List<History>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return histories
    }

    fun getHistoryByCity(cityId: Int): LiveData<List<History>>{
        val histories: MutableLiveData<List<History>> = MutableLiveData()
        val client = ApiConfig.getApiService().getHistoryByCity(cityId)

        client.enqueue(object : Callback<List<History>>{
            override fun onResponse(call: Call<List<History>>, response: Response<List<History>>) {
                if (response.isSuccessful){
                    histories.value = response.body()
                }
                Log.d(TAG,response.body().toString())
            }

            override fun onFailure(call: Call<List<History>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return histories

    }

    fun getHistory(id: Int): LiveData<History>{
        val history: MutableLiveData<History> = MutableLiveData()
        val client = ApiConfig.getApiService().getHistory(id)

        client.enqueue(object : Callback<History>{
            override fun onResponse(call: Call<History>, response: Response<History>) {
                if (response.isSuccessful){
                    history.value = response.body()
                }
                Log.d(TAG,response.body().toString())
            }

            override fun onFailure(call: Call<History>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return history

    }

    fun postToken(token: DeviceToken){
        CoroutineScope(Dispatchers.IO).launch {
//            val jsonObject = JSONObject()
//            jsonObject.put("token",token.token)
//
//            val jsonObjectString = jsonObject.toString()
//
//            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val client = ApiConfig.getApiService()
//            val response = client.savePost(requestBody)
            client.savePost(token).enqueue(object : Callback<DeviceTokenResponse>{

                override fun onResponse(
                    call: Call<DeviceTokenResponse>,
                    response: Response<DeviceTokenResponse>
                ) {
                    Log.d("POST",response.toString())
                }

                override fun onFailure(call: Call<DeviceTokenResponse>, t: Throwable) {
                    Log.d("POST","Error")
                }

            })
        }
    }

}