package uz.gita.jaxongir.sellmanageradmin.presenter.invalid

import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ExpiredDirection {
    suspend fun back()
}

@Singleton
class ExpiredDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
):ExpiredDirection {
    override suspend fun back() {
        appNavigator.back()
    }
}