package uz.gita.jaxongir.sellmanageradmin.data.requests

data class ProductRequest(
    val id : String,
    val name : String,
    val count : Int,
    val initialPrice : Int,
    val isValid : Boolean,
    val comment : String
)
