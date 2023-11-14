package com.example.myapplication.firestore

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirestoreModule {

    @Singleton
    @Provides
    fun provideFirestoreInstance() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}