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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.model.PostData
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import com.denuncia.sivar.ui.theme.gray

@Composable

fun PostComp(post: PostData) {
    var expanded by remember { mutableStateOf(false) }
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
            Image(
                painter = painterResource(id = post.userImage),
                contentDescription = "profilePhoto",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
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
                            text = post.username,
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
                            Image(
                                painter = painterResource(id = R.drawable.ic_more),
                                contentDescription = "More options",
                                modifier = Modifier
                                    .size(17.dp)
                                    .clickable {
                                        expanded = true
                                    }
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
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_delete_user),
                                                contentDescription = "delete",
                                                modifier = Modifier.size(20.dp)
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
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_ntercambiar),
                                                contentDescription = "delete",
                                                modifier = Modifier.size(20.dp)
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_category), contentDescription = "category",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "Categoria:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.category,
                        color = gray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_status), contentDescription = "status",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "Estado:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.status,
                        color = gray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location), contentDescription = "location",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "Ubicación:",
                        fontWeight = FontWeight.Bold,
                        color = gray,
                    )
                    Text(
                        text = post.location,
                        color = gray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_timer), contentDescription = "timer",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = post.timer,
                        color = gray,
                    )
                }
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = post.description,
                    color = blue20
                )
                Spacer(modifier = Modifier.size(5.dp))
                Image(
                    painter = painterResource(id = post.image),
                    contentDescription = "photoDenuncia",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                )
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
                            text = post.supportCount.toString(),
                            fontSize = 13.sp,
                            color = colorResource(id = R.color.white),
                        )
                    }

                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PostCompPreview() {
    PostComp(
        PostData(
            1,
            R.drawable.photoexample,
            "Roberto Loza",
            "Robo",
            "Pendiente",
            "San Salvador",
            "Hace dos horas",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            R.drawable.photodenuncia,
            5
        )
    )
}