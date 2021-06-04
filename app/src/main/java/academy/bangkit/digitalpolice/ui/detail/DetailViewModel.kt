package academy.bangkit.digitalpolice.ui.detail

import academy.bangkit.digitalpolice.core.data.AppRepository
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DetailViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var historyId = 0

    fun selectedHistory(historyId: Int){
        this.historyId = historyId
    }

    fun getHistory() : LiveData<History> = appRepository.getHistory(historyId)
}