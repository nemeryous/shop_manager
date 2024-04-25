package com.example.shopmanagement.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.navigation.NavigationDestination

object CategoryAddDestination : NavigationDestination {
    override val route: String = "category_add"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun CategoryAddScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val categoryAddUiState by viewModel.categoryAddUiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        OutlinedTextField(
            value = categoryAddUiState.categoryName,
            onValueChange = viewModel::updateCategoryName,
            label = {
                Text(text = stringResource(id = R.string.category_name))
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
        )


        OutlinedTextField(
            value = categoryAddUiState.categoryDesc,
            onValueChange = viewModel::updateCategoryDesc,
            label = {
                Text(text = stringResource(id = R.string.category_desc))
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
        )


        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = {},

            ) {
            Text(text = stringResource(id = R.string.add_category))
        }


    }
}

@Preview(showBackground = true)
@Composable
fun CategoryAddPreview() {
    CategoryAddScreen()
}