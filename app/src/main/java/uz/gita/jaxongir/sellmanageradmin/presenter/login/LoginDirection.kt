package uz.gita.jaxongir.sellmanageradmin.presenter.login

import uz.gita.jaxongir.sellmanageradmin.presenter.main.MainScreen
import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface LoginDirection {
   suspend fun moveToMainScreen()
}
@Singleton
class LoginDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : LoginDirection{
    override suspend fun moveToMainScreen() {
        appNavigator.replaceScreen(MainScreen())
    }

}