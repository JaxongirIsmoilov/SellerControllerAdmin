package uz.gita.jaxongir.sellmanageradmin.presenter.seller

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import uz.gita.jaxongir.sellmanageradmin.ui.components.SellerItem


object SellerTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(id = R.drawable.ic_sellers)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Sellers",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = getViewModel<SellerViewModel>()
        SellerScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }

}

lateinit var changeTab: () -> Unit

fun setListener(block: () -> Unit) {
    changeTab = block
}

@Composable
fun SellerScreenContent(
    uiState: State<SellerScreenContract.UIState>,
    onEventDispatcher: (SellerScreenContract.Intent) -> Unit,
) {
    onEventDispatcher.invoke(SellerScreenContract.Intent.Load)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Sellers ",
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    IconButton(onClick = { onEventDispatcher(SellerScreenContract.Intent.MoveToAdd) }) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Right Icon",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            LazyColumn {
                items(uiState.value.usersList) { data ->
                    SellerItem(
                        sellerData = data,
                        onClickDelete = {
                            onEventDispatcher.invoke(SellerScreenContract.Intent.DeleteSeller(data))
                        },
                        onClickEdit = {
                            onEventDispatcher.invoke(SellerScreenContract.Intent.MoveToEditScreen(it))
                        }
                    )
                }
            }
        }

    }
}