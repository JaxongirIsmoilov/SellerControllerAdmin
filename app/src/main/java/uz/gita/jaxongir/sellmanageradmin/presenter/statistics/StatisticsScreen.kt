package uz.gita.jaxongir.sellmanageradmin.presenter.statistics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.jaxongir.sellmanageradmin.R

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

    }

}
