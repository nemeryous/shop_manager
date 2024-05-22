package com.example.shopmanagement.ui.admin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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


object BrandAddDestination : NavigationDestination {
    override val route: String = "brand_add"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun BrandAddScreen(
    modifier: Modifier = Modifier,
    viewModel: BrandAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val brandAddUiState by viewModel.brandAddUiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()


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
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        OutlinedTextField(
            value = brandAddUiState.brandName,
            onValueChange = viewModel::updateBrandName,
            label = {
                Text(text = stringResource(id = R.string.brand_name))
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { viewModel.isChooseImage = !viewModel.isChooseImage },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.add_image))
        }

        OutlinedTextField(
            value = brandAddUiState.brandDescription,
            onValueChange = viewModel::updateBrandDescription,
            label = {
                Text(text = stringResource(id = R.string.brand_desc))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = { coroutineScope.launch {
                viewModel.addBrand(bitmap.value)
            } },

            ) {
            Text(text = stringResource(id = R.string.add_brand))
        }

        if (viewModel.isChooseImage) {
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
                                viewModel.isChooseImage = false
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
                                viewModel.isChooseImage = false
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