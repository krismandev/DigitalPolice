package academy.bangkit.digitalpolice.core.data

import academy.bangkit.digitalpolice.core.data.source.AppDataSource
import academy.bangkit.digitalpolice.core.data.source.remote.RemoteDataSource
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.DeviceToken
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import androidx.lifecycle.LiveData

class AppRepository private constructor(private val remoteDataSource: RemoteDataSource) : AppDataSource {
    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(remoteData: RemoteDataSource): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData).apply { instance = this }
            }
    }

    override fun getCities(): LiveData<List<City>> {
        return remoteDataSource.getCities()
    }

    override fun getHistories(): LiveData<List<History>> {
        return remoteDataSource.getHistories()
    }

    override fun getHistoriesToday(): LiveData<List<History>> {
        return remoteDataSource.getHistoriesToday()
    }

    override fun getHistoryByCity(cityId: Int): LiveData<List<History>> {
        return remoteDataSource.getHistoryByCity(cityId)
    }

    override fun getHistory(id: Int): LiveData<History> {
        return remoteDataSource.getHistory(id)
    }

    override fun postToken(token: DeviceToken) {
        return remoteDataSource.postToken(token)
    }




}