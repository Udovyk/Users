package com.udovyk.users.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.udovyk.users.R
import com.udovyk.users.ext.getViewModelOfType
import com.udovyk.users.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).getViewModelOfType()
    }
}
