package com.app.denuncia.sivar.ui.register.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20

@Composable
fun RegisterTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    height: Dp = 50.dp
){
    // Texto arriba de TextField
    Text(
        modifier = modifier.fillMaxWidth(),
        text = label,
        fontSize = 15.sp,
        color = blue20
    )
    Spacer(modifier = Modifier.height(2.dp))
    Row(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier,
                    color = blue20.copy(alpha = 0.5f),
                    fontFamily = IstokWebFamily,
                    fontSize = 15.sp
                )
            },
            keyboardOptions = keyboardOptions,
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
    }
}

