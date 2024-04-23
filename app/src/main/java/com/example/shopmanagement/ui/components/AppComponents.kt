package com.example.shopmanagement.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.theme.Primary
import com.example.shopmanagement.ui.theme.Secondary


@Composable
fun NormalTextComponent(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.black)

    )
}

@Composable
fun BoldTextComponent(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value, modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), style = TextStyle(
            fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal
        ), textAlign = TextAlign.Center

    )
}

@Composable
fun MyTextFieldWithIcon(
    value: String,
    onStringValueChange: (String) -> Unit = {},
    label: String,
    imageVector: ImageVector,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onStringValueChange,
        label = {
            Text(text = label)
        },
        keyboardOptions = keyboardOptions,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        },

        )
}

@Composable
fun MyPasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit = {},
    label: String,
    imageVector: ImageVector,
    passwordVisible: Boolean,
    onPasswordVisibleChange: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default

) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = {
            Text(text = label)
        },
        keyboardOptions = keyboardOptions,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if (passwordVisible) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick = onPasswordVisibleChange) {

            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()


    )
}


@Composable
fun CheckboxComponent(
    checkedState: Boolean, onCheckedChange: (Boolean) -> Unit, privacyAndPolicy: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checkedState, onCheckedChange = onCheckedChange)

        ClickableTextComponent(value = privacyAndPolicy)
    }
}

@Composable
fun ClickableTextComponent(value: String) {

    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle()) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle()) {
            pushStringAnnotation(termsAndConditionsText, termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("ClickableTextComponent", "{$span}")
        }
    })
}

@Composable
fun ButtonComponent(
    value: String,
    isEnable: Boolean,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnable
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ), contentAlignment = Alignment.Center

        ) {

            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}

@Composable
fun DivideTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )

        Text(
            text = stringResource(id = R.string.or),
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )
    }
}

@Composable
fun ClickableLoginTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Already have an account? "
    val loginText = "Login"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("ClickableTextComponent", "{$span}")

            if (span.item == loginText) {
                onTextSelected(span.item)
            }

        }
    })
}

@Composable
fun ClickableRegisterComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Don't have an account yet? "
    val registerText = "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = registerText, annotation = registerText)
            append(registerText)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("ClickableRegisterComponent", "{$span}")

            if (span.item == registerText) {
                onTextSelected(span.item)
            }

        }
    })
}

@Composable
fun ErrorTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        ),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
fun IconComponent(modifier: Modifier = Modifier, imageVector: ImageVector) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = modifier
    )
}

@Preview
@Composable
fun TestComposable() {
    ButtonComponent(value = "Login", isEnable = true, onClick = {})
}