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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.scale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object ProductAddDestination : NavigationDestination {
    override val route: String = "product_add"

    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun ProductAddScreen(
    productAddViewModel: ProductAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()

    val productAddUiState by productAddViewModel.productAddUiState.collectAsState()
    val context = LocalContext.current
    val img: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.reebok)
    val bitmap = remember {
        mutableStateOf(img)
    }
    val scaledBitmap = bitmap.value.scale(1024, 1024, true)

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

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row() {
                Icon(Icons.Filled.ArrowBackIosNew, contentDescription = null)
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = stringResource(id = R.string.add_product),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

            }

            TextField(
                value = productAddUiState.productName,
                onValueChange = productAddViewModel::updateProductName,
                label = { Text(stringResource(id = R.string.product_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = productAddUiState.productPrice,
                onValueChange = productAddViewModel::updateProductPrice,
                label = { Text(stringResource(id = R.string.product_price)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = productAddUiState.productDescription,
                onValueChange = productAddViewModel::updateProductDescription,
                label = { Text(stringResource(id = R.string.product_desc)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = productAddViewModel::onClickAddImage,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Add image")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { productAddViewModel.onExpanded() }
            ) {
                Text(
                    text = productAddUiState.selectedCategory
                        ?: stringResource(id = R.string.select_category),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            DropdownMenu(
                expanded = productAddViewModel.expanded,
                onDismissRequest = { productAddViewModel.expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Category 1", "Category 2", "Category 3").forEach { category ->
                    DropdownMenuItem(
                        text = category,
                        onClick = {
                            productAddViewModel.updateSelectedCategory(category)
                            productAddViewModel.expanded = false
                        }
                    )
                }
            }


            Image(bitmap = scaledBitmap.asImageBitmap(), contentDescription = "")



            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick =
                {
                    coroutineScope.launch {
                        productAddViewModel.addProduct(bitmap.value)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.add_product))
            }
        }
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
                                Icons.Filled.CameraAlt,
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
                                Icons.Filled.AddAPhoto,
                                contentDescription = "Gallery",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.gallery),
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun ProductAddPreview() {
    ProductAddScreen()
}
