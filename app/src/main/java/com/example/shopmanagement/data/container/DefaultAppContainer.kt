package com.example.shopmanagement.data.container

import android.content.Context
import com.example.shopmanagement.data.service.AccountService
import com.example.shopmanagement.data.service.impl.AccountServiceImpl
import com.example.shopmanagement.data.service.module.FirebaseModule




class DefaultAppContainer(private val context: Context) : AppContainer {
    override val accountService: AccountService by lazy {
        AccountServiceImpl(FirebaseModule.auth())
    }
}