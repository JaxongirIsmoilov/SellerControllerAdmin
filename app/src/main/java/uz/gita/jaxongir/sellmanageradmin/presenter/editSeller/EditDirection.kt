package uz.gita.jaxongir.sellmanageradmin.presenter.editSeller

import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface EditDirection {
    suspend fun back()
}
@Singleton
class EditDirectionImpl @Inject constructor(
    val appNavigator: AppNavigator,
) : EditDirection {
    override suspend fun back() {
        appNavigator.back()
    }
}