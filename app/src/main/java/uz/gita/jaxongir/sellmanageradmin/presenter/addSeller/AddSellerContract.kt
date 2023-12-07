package uz.gita.jaxongir.sellmanageradmin.presenter.addSeller

import kotlinx.coroutines.flow.StateFlow

interface AddSellerContract {
    interface ViewModel {
        fun onEventDispatcher(intent: Intent)

        val sideEffect : StateFlow<SideEffect>
    }

    interface Intent {
        data class AddSeller(val name:String, val password:String) : Intent
        object Back: Intent
    }

    interface SideEffect {
        object Init : SideEffect
        data class ShowNotification(val message : String= "") : SideEffect
        data class ShowSuccessMessage(val message : String= "") : SideEffect
    }



}