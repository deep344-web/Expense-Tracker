package com.example.myapplication.google_sign_in

import android.content.Context
import com.example.myapplication.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GoogleAuthClientModule {

    @Singleton
    @Provides
    fun providesGoogleAuthClient(@ApplicationContext appContext: Context) : GoogleAuthUiClient {
        return GoogleAuthUiClient(appContext, Identity.getSignInClient(appContext))
    }
}