package com.udovyk.users.di.module

import com.udovyk.users.ui.userdetail.UserDetailFragment
import com.udovyk.users.ui.userslist.UsersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindUsersListFragment(): UsersListFragment

    @ContributesAndroidInjector
    abstract fun bindUserDetailFragment(): UserDetailFragment

}