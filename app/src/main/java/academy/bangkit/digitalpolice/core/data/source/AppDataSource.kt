package academy.bangkit.digitalpolice.core.data.source

import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import androidx.lifecycle.LiveData

interface AppDataSource {
    fun getCities(): LiveData<List<City>>

    fun getHistories(): LiveData<List<History>>

    fun getHistoryByCity(cityId: Int): LiveData<List<History>>
}