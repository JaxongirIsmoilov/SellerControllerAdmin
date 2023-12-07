package uz.gita.jaxongir.sellmanageradmin.di

import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FireBaseModule {
    @[Provides Singleton]
    fun firestoreProvider(): FirebaseFirestore = Firebase.firestore

    @[Provides Singleton]
    fun realTimeDatabaseProvider(): FirebaseDatabase = Firebase.database
}