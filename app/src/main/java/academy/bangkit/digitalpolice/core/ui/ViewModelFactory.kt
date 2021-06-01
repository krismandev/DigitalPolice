package academy.bangkit.digitalpolice.core.ui

import academy.bangkit.digitalpolice.core.data.AppRepository
import academy.bangkit.digitalpolice.di.Injection
import academy.bangkit.digitalpolice.ui.home.HomeViewModel
import academy.bangkit.digitalpolice.ui.notifications.NotificationsViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val appRepository: AppRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(appRepository) as T
            }

            modelClass.isAssignableFrom(NotificationsViewModel::class.java) -> {
                return NotificationsViewModel(appRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }


}