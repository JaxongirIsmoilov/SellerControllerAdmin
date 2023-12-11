package uz.gita.jaxongir.sellmanageradmin.presenter.invalid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.jaxongir.sellmanageradmin.R
import uz.gita.jaxongir.sellmanageradmin.ui.components.ExpiredProductItem

object ExpiredTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(id = R.drawable.ic_expired)

            return remember {
                TabOptions(
                    index = 2u,
                    title = "Expired",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = getViewModel<ExpiredViewModel>()
        ExpiredContent(uiState = viewModel.uiState.collectAsState())
    }

}

@Composable
fun ExpiredContent(
    uiState: State<ExpiredContract.UIState>,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Expired Products ",
                        textAlign = TextAlign.Center
                    )
                }
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            LazyColumn {
                items(uiState.value.productList) { data ->
                    ExpiredProductItem(
                        productData = data
                    )
                }
            }
        }

    }

}