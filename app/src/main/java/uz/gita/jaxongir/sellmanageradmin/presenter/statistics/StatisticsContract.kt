package uz.gita.jaxongir.sellmanageradmin.presenter.statistics

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData

interface StatisticsContract {
    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val ls: List<ProductData> = emptyList(),
        var name: String = "",
        val sellerSalesMap: Map<String, Double> = emptyMap()
    )

    interface Intent {
        data class Name(
            val name: String = "",
        ) : Intent
    }
}