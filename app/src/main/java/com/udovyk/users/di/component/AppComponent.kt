package com.udovyk.users.di.component

import android.app.Application
import com.udovyk.users.di.module.*
import com.udovyk.users.ui.app.AppUsers
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelBuilder::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        NetworkModule::class])
interface AppComponent : AndroidInjector<AppUsers> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    override fun inject(instance: AppUsers)

}