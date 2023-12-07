package uz.gita.jaxongir.sellmanageradmin.utills

import com.google.firebase.database.DataSnapshot
import timber.log.Timber
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData

fun myLog(message : String){
    Timber.tag("TTT").d(message)
}

fun DataSnapshot.toProductData() : ProductData = ProductData(
    id = child("id").getValue(String::class.java) ?: "",
    name = child("name").getValue(String::class.java) ?: "",
    count = child("count").getValue(Int::class.java) ?: 0,
    initialPrice = child("initialPrice").getValue(Double::class.java) ?: 0.0,
    isValid = child("isValid").getValue(Boolean::class.java) ?: true,
    comment = child("comment").getValue(String::class.java) ?: ""
)