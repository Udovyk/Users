package com.udovyk.users.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideContext(application: Application): Context = application
}