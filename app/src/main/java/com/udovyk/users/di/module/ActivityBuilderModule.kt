package com.udovyk.users.di.module

import androidx.appcompat.app.AppCompatActivity
import com.udovyk.users.di.scope.ActivityScope
import com.udovyk.users.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {
    @Binds
    @ActivityScope
    fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun contributeMainActivity(): MainActivity
}