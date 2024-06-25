package com.app.denuncia.sivar.ui.components.PostComponent

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.remote.model.EstadoList
import com.app.denuncia.sivar.model.mongoose.publicacion
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownEstatus
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import com.denuncia.sivar.ui.theme.gray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun PostComp(post: publicacion, viewModelMain: ViewModelMain) {

    var estado by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val profile by viewModelMain.profile.collectAsState()

    //Email
    var launchSendEmail by remember { mutableStateOf(false) }
    val loadingEmail by viewModelMain.loadingEmail.collectAsState()
    val stateEmail by viewModelMain.email.collectAsState()
    val detailsError by viewModelMain.detailsErrorEmail.collectAsState()

    //Support
    val code by viewModelMain.code.collectAsState()
    val txtCode = remember { mutableStateOf("") }
    var launchSupport by remember { mutableStateOf(false) }
    val supportState by viewModelMain.supportState.collectAsState()
    val supportDetails by viewModelMain.supportDetails.collectAsState()
    val loadingSupport by viewModelMain.loadingSupport.collectAsState()

    //Update
    val updateComplaint by viewModelMain.updateComplaint.collectAsState()
    val detailsUpdate by viewModelMain.detailsUpdateComplaint.collectAsState()
    var launchUpdate by remember { mutableStateOf(false) }

    //Delete
    var launchDelete by remember { mutableStateOf(false) }
    val stateDelete by viewModelMain.deleteComplaint.collectAsState()
    val detailsDelete by viewModelMain.detailsDeleteComplaint.collectAsState()

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
            if(post.usuario != null){
                if(post.usuario!!.image.url.isNotEmpty()){
                    AsyncImage(
                        model = "https://${post.usuario!!.image.url.removePrefix("http://")}",
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
                            text = if(post.usuario != null) post.usuario!!.username else "Usuario",
                            fontWeight = FontWeight.Bold,
                            color = blue20,
                        )
                        Text(
                            text = "realizó una denuncia",
                            color = blue20,
                        )
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (profile.rol == "Administrador" || post.usuario!!._id == profile._id) {
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
                                        onClick = {
                                            CoroutineScope(Dispatchers.IO).launch {
                                                viewModelMain.deleteComplaint(post._id)
                                                delay(1000)
                                                launchDelete = true
                                            }
                                        }
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
                        text = "Categoría:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.categoria.name,
                        color = gray
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
                    if (profile.rol == "Administrador"){
                        Text(
                            text = "Estado:",
                            fontWeight = FontWeight.Bold,
                            color = gray,
                        )
                        CustomDropdownEstatus(
                            options = EstadoList,
                            selectedOption = estado.ifEmpty { post.state }
                        ) {
                            estado = it.nombre
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModelMain.updateComplaint(post._id, estado)
                                delay(1000)
                                launchUpdate = true
                            }
                        }
                    }else{
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

                    if(post.usuario != null){
                        if(post.usuario!!._id != profile._id){
                            Button(
                                onClick = {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        viewModelMain.getEmailCode()
                                        delay(1000)
                                        launchSendEmail = true
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(30.dp),
                                enabled = post.apoyo.find{ it.usuario == profile._id } == null,
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blue80)
                            ){
                                Text(
                                    text = if(post.apoyo.find{ it.usuario == profile._id } != null) "Denuncia apoyada" else "Apoyar denuncia",
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
                        }else{
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .weight(1f)
                                    .height(30.dp),
                                enabled = false,
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blue80)
                            ){
                                Text(
                                    text = "Apoyos de tu denuncia:  ${post.apoyo.size}",
                                    color = blue20,
                                )
                            }

                        }
                    }else{
                        Button(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    viewModelMain.getEmailCode()
                                    delay(1000)
                                    launchSendEmail = true
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(30.dp),
                            enabled = post.apoyo.find{ it.usuario == profile._id } == null,
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = blue80)
                        ){
                            Text(
                                text = if(post.apoyo.find{ it.usuario == profile._id } != null) "Denuncia apoyada" else "Apoyar denuncia",
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

    //Email support
    if (launchSendEmail) {
        if(!loadingEmail){
            if(stateEmail){
                AlertDialog(
                    modifier = Modifier.border(1.dp, blue20, RoundedCornerShape(20.dp)),
                    containerColor = blue100,
                    onDismissRequest = { },
                    confirmButton = {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(blue80)
                                .height(30.dp)
                                .padding(start = 10.dp, end = 10.dp)
                                .clickable(onClick = {
                                    if (txtCode.value == code) {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            viewModelMain.supportComplaint(post._id)
                                            delay(1000)
                                            launchSupport = true
                                        }
                                    } else {
                                        Toast
                                            .makeText(
                                                context,
                                                "El codigo ingresado no es el correcto!",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = "mail", tint = blue20, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(text = "Confirmar", color = blue20)
                        }
                    },
                    dismissButton = {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(blue80)
                                .height(30.dp)
                                .padding(start = 10.dp, end = 10.dp)
                                .clickable(onClick = {
                                    launchSendEmail = false
                                    launchSupport = false
                                }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Default.Cancel, contentDescription = "mail", tint = blue20, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(text = "Cancelar", color = blue20)
                        }
                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Digite el codigo que se le envio a su correo electrónico para apoyar la denuncia de: ${if(post.usuario != null) post.usuario!!.username else "Usuario"}!",
                                color = blue20,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = IstokWebFamily
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            OutlinedTextField(
                                colors = OutlinedTextFieldDefaults.colors(
                                    cursorColor = blue20,
                                    focusedBorderColor = blue20,
                                    unfocusedBorderColor = blue20,
                                    disabledBorderColor = blue20,
                                    errorBorderColor = blue20,
                                    errorLabelColor = blue20,
                                    errorLeadingIconColor = blue20,
                                    errorTrailingIconColor = blue20,
                                    errorPlaceholderColor = blue20,
                                    focusedLabelColor = blue20,
                                    unfocusedLabelColor = blue20,
                                    errorTextColor = Color.Red,
                                ),
                                value = txtCode.value,
                                onValueChange = { txtCode.value = it },
                                label = {
                                    Text(text = "Ingrese su código")
                                }
                            )
                        }
                    }
                )
            }else{
                if(detailsError.isNotEmpty()){
                    Toast.makeText(context, detailsError, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Error al enviar el código a su correo", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            AlertDialog(
                modifier = Modifier.border(1.dp, blue20, RoundedCornerShape(20.dp)),
                containerColor = blue100,
                text = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Enviando codigo de apoyo a su correo por favopr espere!",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                onDismissRequest = { },
                confirmButton = {}
            )
        }
    }

    //Support complaint
    if(launchSupport){
        if(!loadingSupport){
            if(supportState){
                Toast.makeText(context, supportDetails, Toast.LENGTH_SHORT).show()
                launchSupport = false
                launchSendEmail = false
                viewModelMain.getComplainst("","","")
            }else{
                Toast.makeText(context, supportDetails, Toast.LENGTH_SHORT).show()
            }
        }else{
            AlertDialog(
                modifier = Modifier.border(1.dp, blue20, RoundedCornerShape(20.dp)),
                containerColor = blue100,
                text = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Enviando informacion por favor espere..",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                onDismissRequest = { },
                confirmButton = {}
            )
        }
    }

    //Update Complaint
    if(launchUpdate){
        if(updateComplaint){
            Toast.makeText(context, detailsUpdate, Toast.LENGTH_SHORT).show()
            launchUpdate = false
            viewModelMain.getComplainst("","","")
        }else{
            Toast.makeText(context, detailsUpdate, Toast.LENGTH_SHORT).show()
            launchUpdate = false
        }
    }

    //Delete Complaint
    if(launchDelete){
        if(stateDelete){
            Toast.makeText(context, detailsDelete, Toast.LENGTH_SHORT).show()
            launchDelete = false
            viewModelMain.getComplainst("","","")
        }else{
            Toast.makeText(context, detailsDelete, Toast.LENGTH_SHORT).show()
            launchDelete = false
        }
    }
}