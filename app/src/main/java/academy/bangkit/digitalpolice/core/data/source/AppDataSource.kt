package academy.bangkit.digitalpolice.core.data.source

import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import androidx.lifecycle.LiveData

interface AppDataSource {
    fun getCities(): LiveData<List<City>>
}