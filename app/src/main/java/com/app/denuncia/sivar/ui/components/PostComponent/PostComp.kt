package com.app.denuncia.sivar.ui.components.PostComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PublishedWithChanges
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import com.denuncia.sivar.ui.theme.gray
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun PostComp(post: publicacion) {
    var expanded by remember { mutableStateOf(false) }

    val formatter = DateTimeFormatter.ISO_DATE_TIME


    fun getTiempo(fecha: String): String {
        val inicio = LocalDateTime.parse(fecha, formatter)
        val fin = LocalDateTime.now()

        val segundos = ChronoUnit.SECONDS.between(inicio, fin)
        val minutes = ChronoUnit.MINUTES.between(inicio, fin)
        val horas = ChronoUnit.HOURS.between(inicio, fin)
        val dias = ChronoUnit.DAYS.between(inicio, fin)
        val mounths = ChronoUnit.MONTHS.between(inicio, fin)
        val years = ChronoUnit.YEARS.between(inicio, fin)

        return if(segundos < 60){
            "hace $segundos segundos"
        }
        else if (minutes < 60 ) {
            "hace $minutes minutos"
        } else if (horas < 24) {
            "hace $horas horas"
        }else if(dias < 30 ){
            "hace $dias dias"
        }else if(mounths < 12){
            "hace $mounths meses"
        }else{
            "hace $years años"
        }
    }

    var rol = "admin"
    OutlinedCard(
        modifier = Modifier
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = blue100,
        ),
        border = BorderStroke(2.dp, blue50),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            if(post.usuario.image.url.isNotEmpty()){
                AsyncImage(
                    model = "https://${post.usuario.image.url.removePrefix("http://")}",
                    contentDescription = "profilePhoto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
            }else{
                AsyncImage(
                    model = "https://cdn-icons-png.freepik.com/512/149/149071.png",
                    contentDescription = "profilePhoto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            text = post.usuario.username,
                            fontWeight = FontWeight.Bold,
                            color = blue20,
                        )
                        Text(
                            text = "ha realizado una denuncia",
                            color = blue20,
                        )
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (rol == "admin") {
                            Icon(
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable {
                                        expanded = true
                                    },
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "senIcon",
                                tint = blue20
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                Column(
                                    modifier = Modifier
                                        .border(1.dp, blue20, RoundedCornerShape(10.dp)),
                                ) {
                                    DropdownMenuItem(
                                        text = { (
                                                Text(
                                                    text = "Eliminar denuncia",
                                                    color = blue20)) },
                                        leadingIcon = {
                                            Icon(
                                                modifier = Modifier
                                                    .size(20.dp),
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "delete",
                                                tint = blue20
                                            )
                                        },
                                        onClick = { expanded = false }
                                    )
                                    DropdownMenuItem(
                                        text = { (
                                                Text(
                                                    text = "Cambiar estado",
                                                    color = blue20)) },
                                        leadingIcon = {
                                            Icon(
                                                modifier = Modifier
                                                    .size(20.dp),
                                                imageVector = Icons.Default.PublishedWithChanges,
                                                contentDescription = "delete",
                                                tint = blue20
                                            )
                                        },
                                        onClick = { expanded = false }
                                    )
                                }

                            }
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.List,
                        contentDescription = "senIcon",
                        tint = gray
                    )
                    Text(
                        text = "Categoria:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.categoria.name,
                        color = gray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Timelapse,
                        contentDescription = "senIcon",
                        tint = gray
                    )
                    Text(
                        text = "Estado:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.state,
                        color = gray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "senIcon",
                        tint = gray
                    )
                    Text(
                        text = "Ubicación:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.departamento,
                        color = gray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "senIcon",
                        tint = gray
                    )
                    Text(
                        text = getTiempo(post.date),
                        color = gray,
                    )
                }
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = post.details,
                    color = blue20
                )
                Spacer(modifier = Modifier.size(5.dp))
                if (post.image.url != "") {
                    AsyncImage(
                        model = "https://${post.image.url.removePrefix("http://")}",
                        contentDescription = "photoDenuncia",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                        )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sign),
                        contentDescription = "sign",
                        modifier = Modifier.size(25.dp)
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .height(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue80)
                        ){
                        Text(
                            text = "Apoyar denuncia",
                            color = blue20,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(30.dp)
                            .background(gray, shape = RoundedCornerShape(50))
                            .clip(RoundedCornerShape(50))
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = post.apoyo.size.toString(),
                            fontSize = 13.sp,
                            color = colorResource(id = R.color.white),
                        )
                    }

                }
            }
        }
    }
}