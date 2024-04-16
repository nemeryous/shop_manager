package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AccountServiceImpl(private val auth: FirebaseAuth) : AccountService {

    override suspend fun createAccountWithEmailAndPassword(
        email: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)

    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }
}