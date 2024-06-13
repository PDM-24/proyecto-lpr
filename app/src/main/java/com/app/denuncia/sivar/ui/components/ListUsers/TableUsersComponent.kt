package com.app.denuncia.sivar.ui.components.ListUsers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50

data class User(val id: Int, val name: String, val email: String, val role: String)

val userList = listOf(
    User(1, "John Doe", "john@example.com", "Admin"),
    User(2, "Jane Smith", "jane@example.com", "User"),
    User(3, "Mike Johnson", "mike@example.com", "User"),
    User(4, "Mike Tayson", "miketayson@example.com", "User"),
    User(5, "Roberto Loza", "lozacast@example.com", "Admin"),
    User(6, "Alice Brown", "alice@example.com", "User"),
    User(7, "Bob Martin", "bob@example.com", "User"),
    User(8, "Charlie Davis", "charlie@example.com", "User"),
    User(9, "Diana Miller", "diana@example.com", "User"),
    User(10, "Eve Wilson", "eve@example.com", "Admin"),
    User(11, "Frank Harris", "frank@example.com", "User"),
    User(12, "Grace Clark", "grace@example.com", "User"),
    User(13, "Hank Lewis", "hank@example.com", "User"),
    User(14, "Ivy Walker", "ivy@example.com", "User"),
    User(15, "Jack Young", "jack@example.com", "Admin"),
    User(16, "Karen Hall", "karen@example.com", "User"),
    User(17, "Larry Allen", "larry@example.com", "User"),
    User(18, "Mona King", "mona@example.com", "User"),
    User(19, "Nina Wright", "nina@example.com", "User"),
    User(20, "Oscar Baker", "oscar@example.com", "User")
)


@Composable
fun UserTable(
    users: List<User>,
    onRoleChange: (User) -> Unit,
    onDelete: (User) -> Unit,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
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
                    text = "ID",
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 6.dp),
                    color = blue20,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 16.sp,
                )
                Text(
                    text = "Nombre",
                    modifier = Modifier
                        .weight(3f)
                        .padding(top = 6.dp),
                    color = blue20,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 16.sp
                )
                Text(
                    text = "Correo",
                    modifier = Modifier
                        .weight(3f)
                        .padding(top = 6.dp),
                    color = blue20,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Rol",
                    modifier = Modifier
                        .weight(2f)
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

        items(users) { user ->
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = user.id.toString(),
                    color = blue20,
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp
                )
                Text(
                    text = user.name,
                    color = blue20,
                    modifier = Modifier.weight(3f),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = user.email,
                    color = blue20,
                    modifier = Modifier.weight(3f),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .weight(2f)
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
                        text = user.role,
                        color = blue20,
                        modifier = Modifier,
                        fontSize = 14.sp
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
        item {
            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                    .collect { visibleItems ->
                        val lastVisibleItem = visibleItems.lastOrNull()
                        if (lastVisibleItem != null && lastVisibleItem.index >= users.size - 1) {
                            onLoadMore()
                        }
                    }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserTablePreview() {
    UserTable(
        users = userList,
        onRoleChange = {},
        onDelete = {},
        onLoadMore = {}
    )
}