package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.core.data.AppRepository
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getCities():LiveData<List<City>> = appRepository.getCities()

    fun getHistories(): LiveData<List<History>> = appRepository.getHistories()

    fun getHistoryByCity(cityId: Int): LiveData<List<History>> = appRepository.getHistoryByCity(cityId)
}