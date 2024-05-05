package com.example.shopmanagement.model

data class ItemHomeAdmin(
    val title: String
)
val itemHomeAdmin = listOf(
    ItemHomeAdmin("Add Product"),
    ItemHomeAdmin("All Product"),
    ItemHomeAdmin("Add Brand"),
    ItemHomeAdmin("All Brand"),
    ItemHomeAdmin("Add Category"),
    ItemHomeAdmin("All Category"),
    ItemHomeAdmin("Log Out"),
)
