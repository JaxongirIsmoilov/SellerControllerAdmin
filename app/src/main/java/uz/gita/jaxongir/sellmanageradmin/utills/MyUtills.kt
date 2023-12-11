package uz.gita.jaxongir.sellmanageradmin.utills

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
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
    soldPrice = child("soldPrice").getValue(Double::class.java) ?: 0.0,
    isValid = child("isValid").getValue(Boolean::class.java) ?: true,
    comment = child("comment").getValue(String::class.java) ?: "",
    sellerName = child("sellerName").getValue(String::class.java) ?: ""
)

fun DataSnapshot.toStatisticsData(): ProductData = ProductData(
    id = child("id").getValue(String::class.java) ?: "",
    name = child("name").getValue(String::class.java) ?: "",
    count = child("sellCount").getValue(Int::class.java) ?: 0,
    initialPrice = child("initialPrice").getValue(Double::class.java) ?: 0.0,
    isValid = child("isValid").getValue(Boolean::class.java) ?: false,
    comment = child("comment").getValue(String::class.java) ?: "",
    sellerName = child("sellerName").getValue(String::class.java) ?: "",
    soldPrice = child("sellingPrice").getValue(Double::class.java) ?: 0.0,
    adminToken = child("adminToken").getValue(String::class.java) ?: ""
)

