package com.example.shopmanagement.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Cases
import androidx.compose.material.icons.outlined.Class
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R

@Composable
fun ViewProfile(

) {
    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ViewProfileContent(

            )
        }
    }
}

@Composable
fun ViewProfileContent(
    profileViewModel: ProfileScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {


    val uiState by profileViewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_background),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        model = "",
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent.copy(alpha = 0.2f),
                                contentColor = Color.White
                            ),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Edit profile",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    Icons.Outlined.Edit,
                                    contentDescription = "Edit profile",
                                )
                            }
                        }
                    }
                }
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Outlined.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Outlined.Class,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Class,
                        contentDescription = "My jobs",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "My orders",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    ),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Icon(
                        Icons.Filled.ChevronRight,
                        contentDescription = "Add Title"
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        contentDescription = "profile",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "About me",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Title"
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Cases,
                        contentDescription = "work experience",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Address",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                IconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Title"
                    )
                }

            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color.White)
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.banner),
//                        contentDescription = "education",
//                        modifier = Modifier.size(24.dp),
//                        tint = MaterialTheme.colorScheme.onTertiaryContainer
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Education",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.onPrimaryContainer
//                    )
//                }
//                IconButton(
//                    onClick = {  },
//                    colors = IconButtonDefaults.iconButtonColors(
//                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
//                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
//                    )
//                ) {
//                    Icon(
//                        Icons.Filled.Add,
//                        contentDescription = "Add Title"
//                    )
//                }
//
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color.White)
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        Icons.Filled.AcUnit,
//                        contentDescription = "skill",
//                        tint = MaterialTheme.colorScheme.onTertiaryContainer
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Skill",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.onPrimaryContainer
//                    )
//                }
//                IconButton(
//                    onClick = {  },
//                    colors = IconButtonDefaults.iconButtonColors(
//                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
//                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
//                    )
//                ) {
//                    Icon(
//                        Icons.Filled.Add,
//                        contentDescription = "Add Title"
//                    )
//                }
//
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color.White)
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        Icons.Filled.Language,
//                        contentDescription = "language",
//                        tint = MaterialTheme.colorScheme.onTertiaryContainer
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Language",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.onPrimaryContainer
//                    )
//                }
//                IconButton(
//                    onClick = {  },
//                    colors = IconButtonDefaults.iconButtonColors(
//                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
//                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.1f)
//                    )
//                ) {
//                    Icon(
//                        Icons.Filled.Add,
//                        contentDescription = "Add Title"
//                    )
//                }
//
//            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ViewProfile()
}
