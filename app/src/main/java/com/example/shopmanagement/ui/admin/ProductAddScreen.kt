package com.example.shopmanagement.ui.admin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.scale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.navigation.NavigationDestination


object ProductAddDestination : NavigationDestination {
    override val route: String = "product_add"

    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun ProductAddScreen(
    modifier: Modifier = Modifier,
    productAddViewModel: ProductAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val productAddUiState by productAddViewModel.productAddUiState.collectAsState()

    val context = LocalContext.current

    val img = BitmapFactory.decodeResource(context.resources, R.drawable.nitro_wallpaper_5000x2813)

    val bitmap = remember {
        mutableStateOf(img)
    }



    val scaleBitmap = bitmap.value.scale(1024, 1024, true)


    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) {
            if (it != null) {
                bitmap.value = it
            }
        }
    val launchImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = it?.let { it1 ->
                ImageDecoder.createSource(context.contentResolver, it1)
            }
            bitmap.value = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }!!
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = productAddUiState.productName,
            onValueChange = productAddViewModel::updateProductName ,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.product_name))
            }
        )
        OutlinedTextField(value = productAddUiState.productQuantity.toString(),
            onValueChange = productAddViewModel::updateProductQuantity,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.product_quantity))

            }
        )
        OutlinedButton(
            onClick =  productAddViewModel::onClickAddImage ,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Add image")
        }

        OutlinedTextField(
            value = productAddUiState.productPrice.toString(),
            onValueChange = productAddViewModel::updateProductPrice,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.product_price))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        OutlinedTextField(value = productAddUiState.productDescription,
            onValueChange = productAddViewModel::updateProductDescription,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.product_desc))

            }
        )

            Image(bitmap = scaleBitmap.asImageBitmap() , contentDescription = "" )


        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(onClick = {}) {
            Text(text = stringResource(id = R.string.add_product))
        }

        if (productAddViewModel.isChooseImage) {
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
                                productAddViewModel.isChooseImage = false
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
                                productAddViewModel.isChooseImage = false
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
fun ProductAddPreview() {
    ProductAddScreen()
}