package uz.gita.jaxongir.sellmanageradmin.data.models

import uz.gita.jaxongir.sellmanageradmin.data.requests.ProductRequest
import java.io.Serializable

data class ProductData(
    val id: String,
    val name: String,
    val count: Int,
    val initialPrice: Double,
    val isValid: Boolean,
    val comment: String
) : Serializable {
    fun toRequest(): ProductRequest = ProductRequest(
        id = id,
        name = name,
        count = count,
        initialPrice = initialPrice,
        isValid = isValid,
        comment = comment
    )
}
