package uz.gita.jaxongir.sellmanageradmin.presenter.editSeller

import android.telephony.gsm.SmsMessage
import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData
import uz.gita.jaxongir.sellmanageradmin.presenter.addSeller.AddSellerContract

interface EditContract {
    interface ViewModel {
        val sideEffect : StateFlow<SideEffect>
        fun eventDispatchers(intent: Intent)
    }


    interface Intent {
        data class EditSeller(
            val sellerData: SellerData
        ) : Intent

        object MoveToSellerScreen : Intent
    }

    interface SideEffect {
        object Init : SideEffect
        data class ShowNotification(val message : String= "") : SideEffect
        data class ShowToast(val message: String = "") : SideEffect
    }
}