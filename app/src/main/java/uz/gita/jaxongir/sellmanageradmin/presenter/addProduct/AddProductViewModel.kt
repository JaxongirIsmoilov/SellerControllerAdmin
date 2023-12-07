package uz.gita.jaxongir.sellmanageradmin.presenter.addProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
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
            is AddProductContract.Intent.AddProduct -> {
                viewModelScope.launch {
                    repository.addProduct(
                        name = intent.name,
                        count = intent.count,
                        initialPrice = intent.initialPrice
                    ).onEach {
                        it.onSuccess {
                            sideEffect.emit(AddProductContract.SideEffect.ShowNotification(it))
                        }
                        it.onFailure {
                            sideEffect.emit(AddProductContract.SideEffect.ShowNotification(it.message.toString()))
                        }
                    }.launchIn(viewModelScope)

                    direction.back()
                }
            }
        }

    }
}