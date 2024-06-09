package com.example.shopmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.shopmanagement.ui.navigation.RootShopNavigation
import com.example.shopmanagement.ui.theme.ShopManagementTheme



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopmanagement.ui.navigation.Graph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopManagementTheme {

                    val navController = rememberNavController()
                    RootShopNavigation(navController)
                }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.sss), // replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(900.dp)
                .align(Alignment.Center),

        )

        // Start button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Surface(
                color = Color.Red.copy(alpha = 0.5f),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .clickable(onClick = { navController.navigate(Graph.AUTH) })
                    .width(200.dp)
                    .height(50.dp)
                    .align(Alignment.End)



            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()

                ) {
                    Text(text = "START", color = Color.White, fontSize = 18.sp)
                }
            }


        }
    }
}