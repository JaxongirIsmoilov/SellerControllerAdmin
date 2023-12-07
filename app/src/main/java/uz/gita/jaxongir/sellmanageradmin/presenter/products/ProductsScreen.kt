package uz.gita.jaxongir.sellmanageradmin.presenter.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.jaxongir.sellmanageradmin.R

object ProductsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(id = R.drawable.ic_products)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Products",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {

    }

}
