package uz.gita.jaxongir.sellmanageradmin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.jaxongir.sellmanageradmin.data.repository.AppRepositoryImpl
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAppRepository(impl : AppRepositoryImpl) : AppRepository
}