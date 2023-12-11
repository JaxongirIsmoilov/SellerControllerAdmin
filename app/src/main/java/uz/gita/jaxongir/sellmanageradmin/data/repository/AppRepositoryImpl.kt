package uz.gita.jaxongir.sellmanageradmin.data.repository

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
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
import uz.gita.jaxongir.sellmanageradmin.utills.MyWorker
import uz.gita.jaxongir.sellmanageradmin.utills.getAllLive
import uz.gita.jaxongir.sellmanageradmin.utills.myLog
import uz.gita.jaxongir.sellmanageradmin.utills.toProductData
import uz.gita.jaxongir.sellmanageradmin.utills.toStatisticsData
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val myPref: MyPref
) : AppRepository {
    val firestoreDatabase = Firebase.firestore
    val realtimeDatabase = Firebase.database
    val realDb = FirebaseDatabase.getInstance()
    override fun addSeller(name: String, password: String): Flow<Result<String>> = callbackFlow {
        val id: String = UUID.randomUUID().toString()

        firestoreDatabase.collection("Sellers").whereEqualTo("name", name).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // Seller with this name already exists
                    trySend(Result.success("This seller already exists. Please choose another name."))
                } else {
                    // Seller name is unique, proceed to add new seller
                    firestoreDatabase.collection("Sellers").document(id)
                        .set(SellerData(id, name, password).toRequest())
                        .addOnSuccessListener {
                            trySend(Result.success("User added successfully."))
                        }
                        .addOnFailureListener {
                            // Handle failure in adding new seller
                            trySend(Result.failure(it))
                        }
                }
            }
            .addOnFailureListener {
                // Handle failure in querying for existing seller
                trySend(Result.failure(it))
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
                sellerId = it.id,
                sellerName = it.data?.getOrDefault("name", "").toString(),
                password = it.data?.getOrDefault("password", "").toString()
            )
        }

    override fun addProduct(productData: ProductData): Flow<Boolean> = callbackFlow {
        myLog("Repository add product")
//        realtimeDatabase.reference.get().addOnSuccessListener { myLog("Success") }
        realDb.getReference("Products")
            .child(productData.name).setValue(productData.toRequest())
            .addOnCanceledListener { myLog("Cancel real time") }
            .addOnCompleteListener { myLog("Complete real time") }
            .addOnSuccessListener {
                myLog("Success")
                trySend(true)
                close()
            }
            .addOnFailureListener {
                myLog("Fail")
                trySend(false)
                close()
            }
        myLog("Up await close")
        awaitClose()
        myLog("Down await close")
    }


//    override fun addProduct(productData: ProductData): Flow<Boolean> = callbackFlow {
//        realtimeDatabase.getReference("Products")
//            .child(productData.name)
//            .run {
//                myLog("Inside Run repo")
//                this.get()
//                    .addOnSuccessListener {
//                        myLog("Inside success repo")
//                        this.child("id").setValue(productData.id)
//                        this.child("name").setValue(productData.name)
//                        this.child("count").setValue(productData.count)
//                        this.child("initialPrice").setValue(productData.initialPrice)
//                        this.child("soldPrice").setValue(productData.soldPrice)
//                        this.child("isValid").setValue(productData.isValid)
//                        this.child("comment").setValue(productData.comment)
//                        this.child("sellerName").setValue(productData.sellerName)
//                        trySend(true) // Sending success status
//                        close() // Close the flow after sending the status
//                    }
//                    .addOnFailureListener { exception ->
//                        myLog("Failed to add product: ${exception.message}")
//                        trySend(false) // Sending failure status
//                        close(exception) // Close the flow with an exception
//                    }
//            }
//        awaitClose()
//    }


//    override fun addProduct(
//        name: String,
//        count: Int,
//        initialPrice: Double,
//    ): Flow<Result<String>> = callbackFlow {
//        val id = UUID.randomUUID().toString()
//        val productData = ProductData(id, name, count, initialPrice, soldPrice = 0.0, isValid = true, comment = "")
//
//        val reference = realtimeDatabase.getReference("Products")
//
//        reference.child(productData.name).setValue(productData)
//            .addOnSuccessListener {
//                trySend(Result.success("Products added successfully"))
//            }
//            .addOnFailureListener {
//                trySend(Result.failure(it))
//            }
//
//        awaitClose()
//    }


    override fun editProduct(productData: ProductData): Flow<Result<String>> = callbackFlow {
        val productRef = realDb.getReference("Products").child(productData.name)

        val updates = hashMapOf<String, Any?>(
            "id" to productData.id,
            "name" to productData.name,
            "count" to productData.count,
            "initialPrice" to productData.initialPrice,
            "isValid" to false,
            "comment" to productData.comment,
            "sellerName" to productData.sellerName
        )

        productRef.updateChildren(updates)
            .addOnSuccessListener {
                trySend(Result.success("Product Updated Successfully"))
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
        realDb.getReference("Products")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    myLog("List Products Repo ${snapshot.children.map { it.toProductData() }.size}")
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

    override fun getSoldProducts(name: String): Flow<List<ProductData>> = callbackFlow{
              realDb.getReference("Sellers").child(name)
                  .addValueEventListener(object : ValueEventListener {
                      override fun onDataChange(snapshot: DataSnapshot) {
                          trySend(snapshot.children.map { it.toStatisticsData() })
                      }

                      override fun onCancelled(error: DatabaseError) {}

                  })
        awaitClose()
    }

    override suspend fun scheduleClearingList(context: Context)  {
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }

    override suspend fun clearSoldProjects() {
        realDb.getReference("Sellers").removeValue()
    }

    override fun getAllProductsInLast24Hours(startDate: String): Flow<List<ProductData>> = callbackFlow{
        realDb.getReference("products")
            .orderByChild("timestamp")
            .startAt(startDate)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(snapshot.children.map { it.toProductData() })
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        awaitClose()
    }



}