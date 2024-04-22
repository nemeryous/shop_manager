package com.example.shopmanagement.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shopmanagement.ui.navigation.NavigationDestination


object ProductAddDestination : NavigationDestination {
    override val route: String = "product_add"

    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun ProductAddScreen(
) {


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = {
                    Text(text = "name")
                }
            )
            OutlinedTextField(value = "",
                onValueChange = {},
                label = {
                    Text(text = "quantity")
                }
            )

            OutlinedButton(onClick = {}) {

            }
        }
    }
}

@Preview
@Composable
fun ProductAddPreview() {
    ProductAddScreen()
}