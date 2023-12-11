package uz.gita.jaxongir.sellmanageradmin.data.models

import uz.gita.jaxongir.sellmanageradmin.data.requests.ProductRequest
import java.io.Serializable

data class ProductData(
    val id: String = "",
    val name: String = "",
    val count: Int = 0,
    val initialPrice: Double = 0.0,
    val soldPrice : Double = 0.0,
    val isValid: Boolean = true,
    val comment: String = "",
    val sellerName : String = "",
    val adminToken : String = ""
) : Serializable {
    fun toRequest(): ProductRequest = ProductRequest(
        id = id,
        name = name,
        count = count,
        initialPrice = initialPrice,
        soldPrice = soldPrice,
        isValid = isValid,
        comment = comment,
        sellerName = sellerName,
        adminToken = adminToken
    )
}
