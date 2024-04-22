package com.example.shopmanagement.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CategoryAddScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Category name")
            },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Category description")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = {},

            ) {
            Text(text = "Add category")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun CategoryAddPreview() {
    CategoryAddScreen()
}