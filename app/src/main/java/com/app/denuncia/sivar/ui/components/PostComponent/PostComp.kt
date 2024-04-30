package com.app.denuncia.sivar.ui.components.PostComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.app.denuncia.sivar.ui.model.PostData
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import com.denuncia.sivar.ui.theme.gray

@Composable

fun PostComp(post: PostData) {
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
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
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
                            .height(30.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue80)
                        ){
                        Text(
                            text = "Apoyar denuncia",
                            color = blue20)
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