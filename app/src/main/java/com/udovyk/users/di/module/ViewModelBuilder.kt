package com.udovyk.users.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udovyk.users.di.ViewModelFactory
import com.udovyk.users.di.annotation.ViewModelKey
import com.udovyk.users.ui.main.MainViewModel
import com.udovyk.users.ui.userdetail.UserDetailViewModel
import com.udovyk.users.ui.userslist.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelBuilder {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    fun bindUsersListViewModel(viewModel: UsersListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    fun bindUserDetailViewModel(viewModel: UserDetailViewModel): ViewModel




}