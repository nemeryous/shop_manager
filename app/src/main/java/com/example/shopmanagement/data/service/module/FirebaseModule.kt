package com.example.shopmanagement.data.service.module

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


object FirebaseModule {

    fun auth(): FirebaseAuth = Firebase.auth

    fun firestore(): FirebaseFirestore = Firebase.firestore

    fun storage(): FirebaseStorage = Firebase.storage


}