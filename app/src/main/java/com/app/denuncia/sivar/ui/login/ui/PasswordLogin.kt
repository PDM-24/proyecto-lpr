package com.app.denuncia.sivar.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordLogin(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    iconResId: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    height: Dp = 50.dp
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .height(height)
                .clip(RoundedCornerShape(20.dp)),
            placeholder = {
                Text(
                    text = placeholder,
                    color = blue20.copy(alpha = 0.5f),
                    fontFamily = IstokWebFamily
                )
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(color = blue20, fontSize = 15.sp, fontFamily = IstokWebFamily),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = blue100,
                unfocusedContainerColor = blue100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        IconButton(
            onClick = { passwordVisible = !passwordVisible },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = if (passwordVisible) "Hide Password" else "Show Password",
                tint = blue20
            )
        }
    }
}
