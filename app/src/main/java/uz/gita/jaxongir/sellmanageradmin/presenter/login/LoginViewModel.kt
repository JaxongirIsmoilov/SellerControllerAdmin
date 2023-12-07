package uz.gita.jaxongir.sellmanageradmin.presenter.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.data.source.local.pref.MyPref
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val myPref: MyPref,
    private val direction: LoginDirection,
    private val repository: AppRepository
) : ViewModel(), LoginContract.ViewModel {
    override val uiState = MutableStateFlow(LoginContract.UIState())

    override fun onEventDispatcher(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.IsLogin -> {
                if (repository.login(intent.name, intent.password)) {
                    viewModelScope.launch {
                        direction.moveToMainScreen()
                    }
                }
            }

            LoginContract.Intent.MoveToMain -> {
                viewModelScope.launch {
                    direction.moveToMainScreen()
                }
            }
        }
    }


}