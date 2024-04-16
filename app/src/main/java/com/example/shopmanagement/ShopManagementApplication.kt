package com.example.shopmanagement

import android.app.Application
import com.example.shopmanagement.data.container.AppContainer
import com.example.shopmanagement.data.container.DefaultAppContainer

class ShopManagementApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

}