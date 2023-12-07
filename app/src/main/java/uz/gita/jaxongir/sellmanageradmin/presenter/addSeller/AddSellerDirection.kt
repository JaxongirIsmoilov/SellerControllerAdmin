package uz.gita.jaxongir.sellmanageradmin.presenter.addSeller

import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface AddSellerDirection {
    suspend fun back()
}
@Singleton
class AddSellerScreenDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): AddSellerDirection{
    override suspend fun back() {
        appNavigator.back()
    }

}