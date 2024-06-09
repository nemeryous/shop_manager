package com.example.shopmanagement.data

import com.example.shopmanagement.data.Resource
import com.example.shopmanagement.model.User
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginUser(email: String, password: String): Flow<String>

    fun registerUser(email: String, password: String, firstName: String, lastName: String): Flow<User?>

    fun getUser(uid: String): Flow<User>

    fun getCurrentUser(): Flow<User>

    fun logout()

    fun fetchAllUser(): Flow<List<User>>

}