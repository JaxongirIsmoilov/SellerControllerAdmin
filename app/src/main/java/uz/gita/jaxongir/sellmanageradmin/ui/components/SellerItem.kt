package uz.gita.jaxongir.sellmanageradmin.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.jaxongir.sellmanageradmin.R
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SellerItem(
    sellerData: SellerData,
    onClickDelete: () -> Unit,
    onClickEdit: (SellerData) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .combinedClickable(onClick = { onClickEdit(sellerData) }, onLongClick = { onClickDelete.invoke() })
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .align(Alignment.CenterStart),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(5.dp)
                        .clip(
                            CircleShape
                        )
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .align(Alignment.Center)
            ) {
                Text(
                    text = sellerData.sellerName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                )


                Text(
                    text = sellerData.password,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 10.dp)
                )
            }
        }
    }
}