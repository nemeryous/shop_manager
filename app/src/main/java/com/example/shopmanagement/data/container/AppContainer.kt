package com.example.shopmanagement.data.container

import com.example.shopmanagement.data.service.AccountService
import com.example.shopmanagement.data.service.impl.AccountServiceImpl
import com.example.shopmanagement.data.service.module.FirebaseModule
import com.google.firebase.auth.FirebaseAuth

interface AppContainer {

    val accountService: AccountService

}
