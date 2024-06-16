package com.app.denuncia.sivar.ui.register.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedDate(
    modifier: Modifier = Modifier,
    label: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    height: Dp = 50.dp
){

    val state = rememberDatePickerState()
    var showDialog by remember {
        mutableStateOf(false)
    }
    val date = state.selectedDateMillis


    //Texto arriba de TextField
    Text(modifier = modifier
        .fillMaxWidth()
        , text = "$label",
        fontSize = 15.sp,
        color = blue20
    )
    Spacer(modifier = Modifier.height(2.dp))
    Row (modifier = modifier
        .height(height)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .height(height)
                .padding(end = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = blue20),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "Date Picker",
                tint = blue80,
            )
        }
        //DatePicker
        if (showDialog){
            DatePickerDialog(onDismissRequest = { showDialog = false },
                confirmButton = { Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = blue20)
                ) {
                    Text(text = "Confirmar",
                        modifier = Modifier,
                        color = blue100,
                        fontFamily = IstokWebFamily,)
                } }) {
                DatePicker(state = state)
            }
        }

        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            placeholder = {

                if (date == null){
                    Text(
                        text = "DD/MM/YYYY",
                        modifier = Modifier,
                        color = blue20.copy(alpha = 0.5f),
                        fontFamily = IstokWebFamily,
                        fontSize = 15.sp
                    )
                }
                else{
                    date.let {

                        //Para insertar fecha
                        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()

                        Text(
                            text = "${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}",
                            modifier = Modifier,
                            color = blue20,
                            fontFamily = IstokWebFamily,
                            fontSize = 15.sp
                        ) }

                }
            },

            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = blue100,
                unfocusedContainerColor = blue100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}