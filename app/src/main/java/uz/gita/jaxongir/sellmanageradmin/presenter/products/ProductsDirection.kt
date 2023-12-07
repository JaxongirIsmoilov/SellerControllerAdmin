package uz.gita.jaxongir.sellmanageradmin.presenter.products

import uz.gita.jaxongir.sellmanageradmin.presenter.addProduct.AddProductScreen
import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ProductsDirection {
    suspend fun moveToAddProductScreen()
}

@Singleton
class ProductsDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ProductsDirection{
    override suspend fun moveToAddProductScreen() {
        appNavigator.addScreen(AddProductScreen())
    }
}