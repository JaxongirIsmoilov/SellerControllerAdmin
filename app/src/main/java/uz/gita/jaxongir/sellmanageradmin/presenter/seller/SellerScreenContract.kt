package uz.gita.jaxongir.sellmanageradmin.presenter.seller

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData

interface SellerScreenContract {

    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val usersList: List<SellerData> = emptyList(),
        var isLoading: Boolean = false
    )

    interface Intent {
        object MoveToAdd : Intent
        data class MoveToEditScreen(val sellerData: SellerData) : Intent
        object Load : Intent
        data class DeleteSeller(
            val data: SellerData,
        ) : Intent

        data class EditSeller(
            val data: SellerData,
        ) : Intent
    }

}