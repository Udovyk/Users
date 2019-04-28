package com.udovyk.users.ui.app

import com.udovyk.users.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppUsers : DaggerApplication() {
        override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent
                .builder()
                .app(this)
                .build()

}