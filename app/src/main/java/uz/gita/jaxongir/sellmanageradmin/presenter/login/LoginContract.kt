package uz.gita.jaxongir.sellmanageradmin.presenter.login

import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    interface ViewModel{
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val loading: Boolean = false,
    )

    interface Intent{
        data class IsLogin(val name : String, val password : String) : Intent
        object MoveToMain : Intent
    }
}