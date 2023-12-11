package uz.gita.jaxongir.sellmanageradmin.presenter.addProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.data.models.ProductData
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import uz.gita.jaxongir.sellmanageradmin.utills.myLog
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val direction: AddProductDirection,
    private val repository: AppRepository
) : ViewModel(), AddProductContract.ViewModel {
    override val sideEffect =
        MutableStateFlow<AddProductContract.SideEffect>(AddProductContract.SideEffect.Init)

    override fun onEventDispatcher(intent: AddProductContract.Intent) {
        when (intent) {

            AddProductContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is AddProductContract.Intent.AddProduct -> {
                myLog("Add Product viewmodel")
                viewModelScope.launch {
                    repository.addProduct(
                        ProductData(
                            id = "${UUID.randomUUID()}",
                            name = intent.name,
                            count = intent.count,
                            initialPrice = intent.initialPrice,
                            soldPrice = 0.0,
                            comment = "",
                            isValid = true,
                            sellerName = ""
                        )
                    ).onEach {
                        direction.back()
                        myLog("Inside On Each viewmodel")
//                        if (it) {
//                            sideEffect.emit(AddProductContract.SideEffect.ShowNotification(it))
//                        } else {
//
//                            sideEffect.emit(AddProductContract.SideEffect.ShowNotification(it.message.toString()))
//                        }


                    }.launchIn(viewModelScope)


                }
            }
        }

    }
}