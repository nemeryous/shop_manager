package com.example.shopmanagement.data

import android.graphics.Bitmap

interface ImageRepository {

    suspend fun addImage(bitmap: Bitmap): String

}