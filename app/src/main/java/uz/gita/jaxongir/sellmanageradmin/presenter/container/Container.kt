package uz.gita.jaxongir.sellmanageradmin.presenter.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.jaxongir.sellmanageradmin.R
import uz.gita.jaxongir.sellmanageradmin.presenter.invalid.ExpiredTab
import uz.gita.jaxongir.sellmanageradmin.presenter.seller.SellerTab
import uz.gita.jaxongir.sellmanageradmin.presenter.seller.setListener
import uz.gita.jaxongir.sellmanageradmin.presenter.products.ProductsTab
import uz.gita.jaxongir.sellmanageradmin.presenter.statistics.StatisticsTab

class Container : AndroidScreen(){
    @Composable
    override fun Content() {
        ContainerContent()
    }
}

@OptIn(ExperimentalVoyagerApi::class)
@Composable
fun ContainerContent() {
    TabNavigator(SellerTab, tabDisposable = {
        TabDisposable(navigator = it, tabs = listOf(SellerTab, ProductsTab, ExpiredTab, StatisticsTab))
    }) { tabNavigator ->
        Scaffold(content = {
            Box(modifier = Modifier.padding(it)) {
                CurrentTab()
            }
        }, bottomBar = {
            BottomNavigation(backgroundColor = Color.White) {
                TabNavigationItem(tab = SellerTab)
                TabNavigationItem(tab = ProductsTab)
                TabNavigationItem(tab = ExpiredTab)
                TabNavigationItem(tab = StatisticsTab)
            }
        })
    }
}
@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    setListener {
        tabNavigator.current = SellerTab
    }
    val isSelected = tabNavigator.current.key == tab.key

    BottomNavigationItem(
        selected = isSelected,
        onClick = { tabNavigator.current = tab },
        selectedContentColor = Color(0xFF51267D),
        unselectedContentColor = Color(0xFF818C99),
        label = {
            Text(
                text = tab.options.title, style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sfprotext_medium)),
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.16.sp,
                    color = if (isSelected) Color(0xFF51267D) else Color(0xFF818C99)
                )
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(
                    when (tab.options.title) {
                        "Sellers" -> {
                            if (tabNavigator.current.options.title == tab.options.title) {
                                R.drawable.ic_sellers_selected
                            } else {
                                R.drawable.ic_sellers
                            }
                        }

                        "Products" -> {
                            if (tabNavigator.current.options.title == tab.options.title) {
                                R.drawable.ic_products_selected
                            } else {
                                R.drawable.ic_products
                            }
                        }

                        "Expired" -> {
                            if (tabNavigator.current.options.title == tab.options.title) {
                                R.drawable.ic_expired_selected
                            } else {
                                R.drawable.ic_expired
                            }
                        }
                        else -> {
                            if (tabNavigator.current.options.title == tab.options.title) {
                                R.drawable.ic_statistics_selected
                            } else {
                                R.drawable.ic_statistics
                            }
                        }

                    }
                ),
                contentDescription = tab.options.title,
                tint = if (isSelected) Color(0xFF51267D) else Color(0xFF818C99)
            )
        }

    )

}