package uz.gita.jaxongir.sellmanageradmin.presenter.addProduct

import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface AddProductDirection {
    suspend fun back()
}
@Singleton
class AddProductDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator,
) : AddProductDirection {
    override suspend fun back() {
        appNavigator.back()
    }
}