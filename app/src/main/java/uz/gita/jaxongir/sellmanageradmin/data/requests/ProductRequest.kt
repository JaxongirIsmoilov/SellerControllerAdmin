package uz.gita.jaxongir.sellmanageradmin.data.requests

data class ProductRequest(
    val id : String = "",
    val name : String = "",
    val count : Int = 0,
    val initialPrice : Double = 0.0,
    val soldPrice: Double = 0.0,
    val isValid : Boolean = true,
    val comment : String = "",
    val sellerName : String = "",
    val adminToken : String = ""
)
