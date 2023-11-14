package com.example.myapplication.firestore

import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.view.repository.SpendingRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(googleAuthUiClient: GoogleAuthUiClient, firestore: FirebaseFirestore) : SpendingRepository {
        return SpendingRepository(googleAuthUiClient, firestore)
    }
}