package uz.gita.jaxongir.sellmanageradmin.presenter.statistics

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.jaxongir.sellmanageradmin.R
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData
import uz.gita.jaxongir.sellmanageradmin.ui.components.format

object StatisticsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(id = R.drawable.ic_statistics)

            return remember {
                TabOptions(
                    index = 3u,
                    title = "Statistics",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = getViewModel<StatisticsViewModel>()
        StatisticsContent(uiState = viewModel.uiState.collectAsState())
    }

}

@Composable
fun StatisticsContent(
    uiState: State<StatisticsContract.UIState>
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Statistics",
                        textAlign = TextAlign.Center
                    )
                }
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            if (uiState.value.ls.isEmpty()) {
                androidx.compose.material3.Text(text = "No sales data available.")
            } else {
                LazyColumn {
                    items(uiState.value.ls) {
                        StatisticsItem(it.name, it.soldPrice * it.count)
                        StatisticsComponent(it)
                    }
                }
            }
        }

    }

}

@Composable
fun StatisticsItem(product: String, totalSales: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(text = "Product: $product")
        androidx.compose.material3.Text(text = "Total Sales: $${String.format("%.2f", totalSales)}")
        androidx.compose.material3.Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun StatisticsComponent(
    productCommon: ProductData,
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
            StatisticsInRow(label = "Product Name:", value = productCommon.name)
            StatisticsInRow(label = "Product Count:", value = productCommon.count.toString())
            StatisticsInRow(
                label = "Initial Price:",
                value = "$${productCommon.initialPrice.format(2)}"
            )
            StatisticsInRow(
                label = "Selling Price:",
                value = "$${productCommon.soldPrice.format(2)}"
            )
        }
    }
}

@Composable
fun StatisticsInRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.width(150.dp)
        )
        androidx.compose.material3.Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
    }
}