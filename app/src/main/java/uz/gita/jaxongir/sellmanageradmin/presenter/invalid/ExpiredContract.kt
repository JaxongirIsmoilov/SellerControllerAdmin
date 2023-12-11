package uz.gita.jaxongir.sellmanageradmin.presenter.invalid

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData

interface ExpiredContract {
    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val productList: List<ProductData> = emptyList(),
        var isLoading: Boolean = false
    )

    interface Intent {
        object Back : Intent
    }
}