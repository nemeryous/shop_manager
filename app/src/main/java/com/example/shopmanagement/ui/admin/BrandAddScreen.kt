package com.example.shopmanagement.ui.admin

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R

@Composable
fun BrandAddScreen(modifier: Modifier = Modifier) {
    var isChooseImage by remember {
        mutableStateOf(false)
    }


    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) {

        }
    val launchImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {

    }
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
                Text(text = "Brand name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { isChooseImage = !isChooseImage },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Add image")
        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Brand description")
            },
            modifier = Modifier.fillMaxWidth()
        )

       Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = {},

        ) {
            Text(text = "Add brand")
        }

        if (isChooseImage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(vertical = 24.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(onClick = {
                                launcher.launch()
                                isChooseImage = false
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.adidas),
                                    contentDescription = "Camera",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text(
                                text = "Camera",
                                fontSize = 24.sp
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(onClick = {
                                launchImage.launch("image/*")
                                isChooseImage = false
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.adidas),
                                    contentDescription = "Gallery",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text(
                                text = "Gallery",
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BrandAddPreview() {
    BrandAddScreen()
}