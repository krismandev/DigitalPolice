package academy.bangkit.digitalpolice.di

import academy.bangkit.digitalpolice.core.data.AppRepository
import academy.bangkit.digitalpolice.core.data.source.remote.RemoteDataSource
import android.content.Context

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val remoteDataSource = RemoteDataSource.getInstance()
        return AppRepository.getInstance(remoteDataSource)
    }
}