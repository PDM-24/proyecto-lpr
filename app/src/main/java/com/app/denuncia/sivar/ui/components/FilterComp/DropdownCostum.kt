package com.app.denuncia.sivar.ui.components.FilterComp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.denuncia.sivar.remote.model.Departamentos
import com.app.denuncia.sivar.remote.model.Estado
import com.app.denuncia.sivar.remote.model.mongoose.Categoria
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80


@Composable
fun CustomDropdownDepartment(
    options: List<Departamentos>,
    selectedOption: String,
    onOptionSelected: (Departamentos) -> Unit,
    height: Int,
    background: Color,
) {
    val expanded = remember { mutableStateOf(false) }

    Column {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(background)
                .height(height.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .clickable { expanded.value = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = selectedOption,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 5.dp)
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.background(blue50)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text( text = option.nombre, color = Color.White) },
                        onClick = {
                            onOptionSelected(option)
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun CustomDropdownKind(
    options: List<Categoria>,
    selectedOption: String,
    onOptionSelected: (Categoria) -> Unit,
    height: Int,
    background: Color,
) {
    val expanded = remember { mutableStateOf(false) }

    Column(
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(background)
                .height(height.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .clickable { expanded.value = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = selectedOption,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 5.dp)
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.background(blue50)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text( text = option.name, color = Color.White) },
                        onClick = {
                            onOptionSelected(option)
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomDropdownEstatus(
    options: List<Estado>,
    selectedOption: String,
    onOptionSelected: (Estado) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .height(20.dp)
                .width(150.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded.value = true },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = selectedOption,
                    color = if(isSystemInDarkTheme()) Color(0xFFCCCCCC) else Color(0xFF505050),
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Dropdown Icon",
                    tint = if(isSystemInDarkTheme()) Color(0xFFCCCCCC) else Color(0xFF505050),
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 10.dp, end = 5.dp)
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text( text = option.nombre) },
                        onClick = {
                            onOptionSelected(option)
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomDropdownPreview() {
    CustomDropdownEstatus(
        options = listOf(
            Estado(1, "San Salvador"),
            Estado(2, "Santa Ana"),
            Estado(3, "Sonsonate"),
            Estado(4, "Ahuachapán"),
        ),
        selectedOption = "En Revisión",
        onOptionSelected = {}
    )
}