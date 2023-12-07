package uz.gita.jaxongir.sellmanageradmin.presenter.invalid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.jaxongir.sellmanageradmin.R

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

    }

}
