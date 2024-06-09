package com.example.shopmanagement.ui.admin.user

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.ui.navigation.NavigationDestination

object UserScreenDestination : NavigationDestination {
    override val route: String = "user_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAdminScreen(
    viewModel: UserScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory),
    navigateToUserAdd: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    val columnWeight = 3.3f
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            FloatingActionButton(
                onClick = {navigateToUserAdd()},
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TableCell(text = "Email", weight = columnWeight)
                    TableCell(text = "Name", weight = columnWeight)
                    TableCell(text = "role", weight = columnWeight)
                }
            }

            items(uiState.userList) {user ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    TableCell(text = user.email, weight = columnWeight)
                    TableCell(text = "${user.firstName} ${user.lastName}", weight = columnWeight)
                    TableCell(text = user.role, weight = columnWeight)
                }

            }

        }
    }
}


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}