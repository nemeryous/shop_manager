package com.example.shopmanagement.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.components.BoldTextComponent
import com.example.shopmanagement.ui.components.ButtonComponent
import com.example.shopmanagement.ui.components.CheckboxComponent
import com.example.shopmanagement.ui.components.ClickableLoginTextComponent
import com.example.shopmanagement.ui.components.DivideTextComponent
import com.example.shopmanagement.ui.components.MyPasswordTextField
import com.example.shopmanagement.ui.components.MyTextFieldWithIcon
import com.example.shopmanagement.ui.components.NormalTextComponent
import com.example.shopmanagement.ui.navigation.NavigationDestination
import com.example.shopmanagement.ui.theme.ShopManagementTheme
import kotlinx.coroutines.launch

object SignUpDestination : NavigationDestination {
    override val route: String = "sign_up"
    override val titleRes: Int = R.string.sign_up
}

@Composable
fun SignUpScreen(

    signUpViewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToSignIn: () -> Unit,

) {

    val coroutineScope = rememberCoroutineScope()

    val registrationUiState by signUpViewModel.registrationUiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            NormalTextComponent(value = stringResource(id = R.string.hey_there))

            BoldTextComponent(value = stringResource(id = R.string.create_account))
            MyTextFieldWithIcon(
                value = registrationUiState.firstName,
                label = stringResource(id = R.string.first_name),
                imageVector = Icons.Filled.AccountCircle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = { signUpViewModel.updateFirstName(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyTextFieldWithIcon(
                value = registrationUiState.lastName,
                label = stringResource(id = R.string.last_name),
                imageVector = Icons.Filled.AccountCircle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = { signUpViewModel.updateLastName(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyTextFieldWithIcon(
                value = registrationUiState.email,
                label = stringResource(id = R.string.email),
                imageVector = Icons.Filled.Email,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onStringValueChange = { signUpViewModel.updateEmail(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            MyPasswordTextField(
                password = registrationUiState.password,
                label = stringResource(id = R.string.password),
                imageVector = Icons.Filled.Lock,
                passwordVisible = signUpViewModel.passwordVisible,
                onPasswordChange = { signUpViewModel.updatePassword(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onPasswordVisibleChange = { signUpViewModel.updatePasswordVisible() }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CheckboxComponent(
                checkedState = signUpViewModel.checkedPrivacy,
                onCheckedChange = { signUpViewModel.updatePrivacy(it) },
                privacyAndPolicy = stringResource(
                    id = R.string.term_and_condition
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(
                value = "Register",
                isEnable = signUpViewModel.validateInput(),
                onClick = {
                    coroutineScope.launch {
                        signUpViewModel.signUp(navigateToSignIn)
                    }
                })

            DivideTextComponent()

            ClickableLoginTextComponent(onTextSelected = { navigateToSignIn() })
        }
    }
}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    ShopManagementTheme {
        SignUpScreen(navigateToSignIn = {})

    }
}