package academy.bangkit.digitalpolice.core.data

import academy.bangkit.digitalpolice.core.data.source.AppDataSource
import academy.bangkit.digitalpolice.core.data.source.remote.RemoteDataSource
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
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


}