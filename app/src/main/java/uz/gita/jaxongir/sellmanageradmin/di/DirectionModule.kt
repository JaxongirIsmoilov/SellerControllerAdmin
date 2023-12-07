package uz.gita.jaxongir.sellmanageradmin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.sellmanageradmin.presenter.login.LoginDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.login.LoginDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {
    @Binds
    fun bindsLoginDirection(impl: LoginDirectionImpl) : LoginDirection
}