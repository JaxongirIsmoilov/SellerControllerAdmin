package uz.gita.jaxongir.sellmanageradmin.presenter.editProduct

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData

interface EditProductContract {
    interface ViewModel {
        val sideEffect: StateFlow<SideEffect>
        fun eventDispatchers(intent: Intent)
    }


    interface Intent {
        data class EditSeller(
            val productData: ProductData
        ) : Intent

        object MoveToProductsScreen : Intent
    }

    interface SideEffect {
        object Init : SideEffect
        data class ShowToast(val message: String = "") : SideEffect
    }
}