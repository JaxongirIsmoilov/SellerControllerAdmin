package uz.gita.jaxongir.sellmanageradmin.utills.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias NavigationArgs = Navigator.() -> Unit

interface AppNavigationHandler {
    val uiNavigator: SharedFlow<NavigationArgs>
}