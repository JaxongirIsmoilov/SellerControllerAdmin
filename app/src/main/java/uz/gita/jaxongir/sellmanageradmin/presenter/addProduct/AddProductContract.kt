package uz.gita.jaxongir.sellmanageradmin.presenter.addProduct

import kotlinx.coroutines.flow.StateFlow

interface AddProductContract {
    interface ViewModel {

        val sideEffect : StateFlow<SideEffect>
        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        data class AddProduct(
            val name: String,
            val count: Int,
            val initialPrice: Double,
        ) : Intent
    }

    interface SideEffect {
        object Init : SideEffect
        data class ShowNotification(val message : String= "") : SideEffect
    }
}