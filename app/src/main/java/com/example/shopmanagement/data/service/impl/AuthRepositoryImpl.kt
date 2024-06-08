package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthRepositoryImpl(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) :
    AuthRepository {

    private val dbUser: CollectionReference = firestore.collection("users")
    private val TAG = AuthRepositoryImpl::class.simpleName
    override fun loginUser(email: String, password: String): Flow<String> {
        return callbackFlow {
            var uid = ""
            val result =
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener { result ->
                    if (result != null) {
                        val firebaseUser: FirebaseUser? = auth.currentUser
                        uid = firebaseUser?.uid.toString()

                        trySend(uid)
                    }
                }
                    .addOnFailureListener {
                        trySend(uid)
                    }
            awaitClose { cancel() }

        }
    }

    override fun registerUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Flow<User?> =
        callbackFlow {
            var user: User? = null
            val result =
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser? = auth.currentUser
                        val uid: String? = firebaseUser?.uid
                        if (uid != null) {
                            user = User(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                password = password
                            )
                            dbUser.document(uid).set(user!!)
                            trySend(user)
                        }
                    } else {
                        trySend(user)
                    }
                }
                    .addOnFailureListener {
                        Log.d(TAG, it.message.toString())
                    }
            awaitClose { cancel() }

        }

    override fun getUser(uid: String): Flow<User> = callbackFlow {
        dbUser.document(uid).get().addOnSuccessListener { it ->
            if (it != null) {
                val user = User(
                    firstName = it.data!!["firstName"].toString(),
                    lastName = it.data!!["lastName"].toString(),
                    email = it.data!!["email"].toString(),
                    password = it.data!!["password"].toString(),
                    role = it.data!!["role"].toString()
                )
                trySend(user)
            }
        }
        awaitClose { cancel() }
    }

    override fun getCurrentUser(): Flow<User> = callbackFlow {
        val currentUid = auth.currentUser?.uid
        if (currentUid != null) {
            dbUser.document(currentUid).get().addOnSuccessListener { it ->
                if (it != null) {
                    val user = User(
                        firstName = it.data!!["firstName"].toString(),
                        lastName = it.data!!["lastName"].toString(),
                        email = it.data!!["email"].toString(),
                        password = it.data!!["password"].toString(),
                        role = it.data!!["role"].toString()
                    )
                    trySend(user)
                }
            }
        }

        awaitClose { cancel() }
    }

    override fun logout() {
        auth.signOut()
    }
}