package com.example.shopmanagement.data.service.impl

import android.graphics.Bitmap
import android.util.Log
import com.example.shopmanagement.data.ImageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ImageRepositoryImpl(private val storageRef: FirebaseStorage) : ImageRepository {
    private val TAG = ImageRepositoryImpl::class.simpleName
    override suspend fun addImage(bitmap: Bitmap): String {


        val imageRef = storageRef.reference.child("images/$bitmap")
        val baos = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageData = baos.toByteArray()
        val uploadTask = imageRef.putBytes(imageData)
        val url = suspendCoroutine<String> {continuation ->
            uploadTask.addOnSuccessListener { task ->
                task!!.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()
                    continuation.resume(downloadUrl)
                }
            }
        }
        Log.d(TAG, url)

        return url
    }
}