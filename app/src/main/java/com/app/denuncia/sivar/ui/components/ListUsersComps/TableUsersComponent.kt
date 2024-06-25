package com.app.denuncia.sivar.ui.components.ListUsersComps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50

@Composable
fun UserTable(
    users: List<Usuario>,
    onRoleChange: (Usuario) -> Unit,
    onDelete: (Usuario) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(blue100)
            .border(2.dp, blue50, RoundedCornerShape(20.dp)),
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Usuario",
                    modifier = Modifier
                        .weight(3f)
                        .padding(top = 6.dp),
                    color = blue20,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 16.sp
                )
                Text(
                    text = "Rol",
                    modifier = Modifier
                        .weight(3f)
                        .padding(top = 6.dp),
                    color = blue20,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 16.sp
                )
                Text(
                    text = "Eliminar",
                    modifier = Modifier
                        .weight(2f)
                        .padding(top = 6.dp),
                    color = blue20,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 16.sp
                )
            }
            Divider(
                color = blue50,
                thickness = 1.dp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
            )
        }

        items(users) {  user ->
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = user.username,
                    color = blue20,
                    modifier = Modifier.weight(3f),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .weight(3f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(blue50)
                        .clickable { onRoleChange(user) }
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ChangeCircle,
                        contentDescription = "Cambiar Rol",
                        tint = blue20,
                        modifier = Modifier
                            .size(15.dp)
                    )
                    Text(
                        text = user.rol,
                        color = blue20,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,

                    )
                }
                IconButton(
                    onClick = { onDelete(user) },
                    modifier = Modifier.weight(2f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = blue20
                    )
                }
            }
            Divider(
                color = blue50,
                thickness = 1.dp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
            )
        }
    }
}