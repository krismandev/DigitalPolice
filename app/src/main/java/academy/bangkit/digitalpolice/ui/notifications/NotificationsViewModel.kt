package academy.bangkit.digitalpolice.ui.notifications

import academy.bangkit.digitalpolice.core.data.AppRepository
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getHistoriesToday(): LiveData<List<History>> = appRepository.getHistoriesToday()


}