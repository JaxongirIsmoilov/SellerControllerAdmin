package uz.gita.jaxongir.sellmanageradmin.data.models

import uz.gita.jaxongir.sellmanageradmin.data.requests.SellerRequest
import java.io.Serializable

data class SellerData(
    var sellerId: String,
    var sellerName: String,
    var password: String,
) : Serializable{
    fun toRequest() : SellerRequest = SellerRequest(id = sellerId, name = sellerName, password = password)
}
