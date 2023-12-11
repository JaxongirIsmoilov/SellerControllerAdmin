package uz.gita.jaxongir.sellmanageradmin.presenter.products

import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData
import uz.gita.jaxongir.sellmanageradmin.presenter.addProduct.AddProductScreen
import uz.gita.jaxongir.sellmanageradmin.presenter.editProduct.EditProductScreen
import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ProductsDirection {
    suspend fun moveToAddProductScreen()
    suspend fun moveToEditScreen(productData: ProductData)
}

@Singleton
class ProductsDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ProductsDirection{
    override suspend fun moveToAddProductScreen() {
        appNavigator.addScreen(AddProductScreen())
    }

    override suspend fun moveToEditScreen(productData: ProductData) {
        appNavigator.addScreen(EditProductScreen(productData))
    }
}