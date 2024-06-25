package com.app.denuncia.sivar.ui.register.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    height: Dp = 50.dp
) {
    var passwordVisible by remember { mutableStateOf(false) }

    // Texto arriba de TextField
    Text(
        modifier = modifier.fillMaxWidth(),
        text = label,
        fontSize = 15.sp,
        color = blue20
    )
    Spacer(modifier = Modifier.height(2.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
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
                    fontFamily = IstokWebFamily,
                    fontSize = 15.sp
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
