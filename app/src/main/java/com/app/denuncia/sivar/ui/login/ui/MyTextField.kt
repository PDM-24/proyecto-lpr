package com.denuncia.sivar.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20



@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    iconResId: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    height: Dp = 50.dp
){
    Row (modifier = modifier){
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
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier,
                    fontFamily = IstokWebFamily,
                )},
            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(fontSize = 15.sp, fontFamily = IstokWebFamily),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = if(isSystemInDarkTheme()) blue100 else Color.White,
                unfocusedContainerColor = if(isSystemInDarkTheme()) blue100 else Color.White,
                focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
            )
        )
    }
}
