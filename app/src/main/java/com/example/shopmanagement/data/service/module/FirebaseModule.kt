package com.example.shopmanagement.data.service.module

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


object FirebaseModule {

    fun auth(): FirebaseAuth = Firebase.auth

    fun database(): DatabaseReference = Firebase.database.reference

    fun firestore(): FirebaseFirestore = Firebase.firestore

}