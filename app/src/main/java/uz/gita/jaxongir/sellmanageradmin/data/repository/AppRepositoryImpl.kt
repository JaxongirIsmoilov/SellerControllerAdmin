package uz.gita.jaxongir.sellmanageradmin.data.repository

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData
import uz.gita.jaxongir.sellmanageradmin.data.source.local.pref.MyPref
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import uz.gita.jaxongir.sellmanageradmin.utills.getAllLive
import uz.gita.jaxongir.sellmanageradmin.utills.toProductData
import java.util.UUID
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val myPref: MyPref
) : AppRepository {
    val firestoreDatabase = Firebase.firestore
    val realtimeDatabase = Firebase.database
    override fun addSeller(name: String, password: String): Flow<Result<String>> = callbackFlow {
        val id: String = UUID.randomUUID().toString()
        firestoreDatabase.collection("Sellers").whereEqualTo("name", name).get()
            .addOnSuccessListener {
                trySend(Result.success("This seller already exist Please choose another name"))
            }
            .addOnFailureListener {
                firestoreDatabase.collection("Sellers").document(id)
                    .set(SellerData(id, name, password).toRequest())
                    .addOnSuccessListener {
                        trySend(Result.success("User added successfully"))
                    }
                    .addOnFailureListener {
                        trySend(Result.failure(it))
                    }
            }
        awaitClose()
    }

    override fun editSeller(sellerData: SellerData): Flow<Result<String>> = callbackFlow {
        firestoreDatabase.collection("Sellers").document(sellerData.sellerId)
            .set(sellerData)
            .addOnSuccessListener {
                trySend(Result.success("Seller data updated"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun deleteSeller(sellerData: SellerData): Flow<Result<String>> = callbackFlow {
        firestoreDatabase.collection("Sellers").document(sellerData.sellerId).delete()
            .addOnSuccessListener {
                trySend(Result.success("Seller deleted successfully"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getAllUsers(): Flow<Result<List<SellerData>>> =
        firestoreDatabase.collection("Sellers").getAllLive {
            return@getAllLive SellerData(
                sellerId = it.data?.getOrDefault("id", "").toString(),
                sellerName = it.data?.getOrDefault("name", "").toString(),
                password = it.data?.getOrDefault("password", "").toString()
            )
        }

    override fun addProduct(
        name: String,
        count: Int,
        initialPrice: Double,
    ): Flow<Result<String>> = callbackFlow {
        val id = UUID.randomUUID().toString()
        realtimeDatabase.reference.child("Products").child(id).setValue(ProductData(id = id, name = name, count = count, initialPrice = initialPrice, isValid = true, comment = "").toRequest())
            .addOnSuccessListener {
                trySend(Result.success("Product added successfully"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()

    }

    override fun editProduct(productData: ProductData): Flow<Result<String>> = callbackFlow {
        realtimeDatabase.reference.child("Products").child(productData.id)
            .setValue(productData.toRequest())
            .addOnSuccessListener {
                trySend(Result.success("Product added successfully"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun makeInvalid(productData: ProductData): Flow<Result<String>> = callbackFlow {
        realtimeDatabase.reference.child("Products").child(productData.id).child("isValid")
            .setValue(false)
            .addOnSuccessListener {
                trySend(Result.success("Product is made invalid"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getAllProducts(): Flow<Result<List<ProductData>>> = callbackFlow {
        realtimeDatabase.getReference("Products")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(Result.success(snapshot.children.map { it.toProductData() }))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(Result.failure(error.toException()))
                }
            })
        awaitClose()
    }

    override fun login(name: String, password: String): Boolean {
        myPref.setLogin(true)
        return name == "admin" && password == "123"
    }


}