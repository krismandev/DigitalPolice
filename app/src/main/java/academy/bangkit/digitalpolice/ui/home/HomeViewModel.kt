package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.core.data.AppRepository
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getCities():LiveData<List<City>> = appRepository.getCities()
}