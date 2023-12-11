package uz.gita.jaxongir.sellmanageradmin.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData

interface AppRepository {
    fun addSeller(name: String, password: String): Flow<Result<String>>
    fun editSeller(sellerData: SellerData): Flow<Result<String>>
    fun deleteSeller(sellerData: SellerData): Flow<Result<String>>
    fun getAllUsers(): Flow<Result<List<SellerData>>>

    fun addProduct(
        productData: ProductData
    ): Flow<Boolean>

    fun editProduct(productData: ProductData): Flow<Result<String>>
    fun makeInvalid(productData: ProductData): Flow<Result<String>>
    fun getAllProducts(): Flow<Result<List<ProductData>>>

    fun login(name: String, password: String): Boolean

    fun getSoldProducts(name: String): Flow<List<ProductData>>
    suspend fun scheduleClearingList(context: Context)
    suspend fun clearSoldProjects()
    fun getAllProductsInLast24Hours(startDate: String): Flow<List<ProductData>>
}