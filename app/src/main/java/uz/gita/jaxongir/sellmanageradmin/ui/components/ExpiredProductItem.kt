package uz.gita.jaxongir.sellmanageradmin.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData

@Composable
fun ExpiredProductItem(
    productData: ProductData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
        }
    }
}