package uz.gita.jaxongir.sellmanageradmin.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.sellmanageradmin.R
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsItem(
    productData: ProductData,
    onClickDelete: () -> Unit,
    onClickEdit: (ProductData) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = { onClickEdit(productData) },
                onLongClick = { onClickDelete.invoke() })
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProductDetailRow(label = "Product Name:", value = productData.name)
            ProductDetailRow(label = "Product Count:", value = productData.count.toString())
            ProductDetailRow(
                label = "Initial Price:",
                value = "$${productData.initialPrice.format(2)}"
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_make_expire),
                contentDescription = null,
                modifier = Modifier.size(20.dp).clickable { onClick() })
        }
    }
}

@Composable
fun ProductDetailRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.width(150.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
    }
}

fun Double.format(digits: Int): String = "%.${digits}f".format(this)