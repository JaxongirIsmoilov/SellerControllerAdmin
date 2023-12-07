package uz.gita.jaxongir.sellmanageradmin.presenter.seller

import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData
import uz.gita.jaxongir.sellmanageradmin.presenter.addSeller.AddScreen
import uz.gita.jaxongir.sellmanageradmin.presenter.editSeller.EditScreen
import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject

interface SellerDirection {
    suspend fun moveToAddScreen()
    suspend fun moveToEditScreen(sellerData: SellerData)
}

class SellerDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SellerDirection{
    override suspend fun moveToAddScreen() {
        appNavigator.addScreen(AddScreen())
    }

    override suspend fun moveToEditScreen(sellerData: SellerData) {
        appNavigator.addScreen(EditScreen(sellerData))
    }

}