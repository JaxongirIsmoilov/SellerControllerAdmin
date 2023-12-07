package uz.gita.jaxongir.sellmanageradmin.presenter.addSeller

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
class AddViewModel @Inject constructor(
    private val direction: AddSellerDirection,
    private val repository: AppRepository
) : ViewModel(), AddSellerContract.ViewModel {

    override val sideEffect =
        MutableStateFlow<AddSellerContract.SideEffect>(AddSellerContract.SideEffect.Init)

    override fun onEventDispatcher(intent: AddSellerContract.Intent) {
        when (intent) {
            is AddSellerContract.Intent.AddSeller -> {
                viewModelScope.launch {
                    repository.addSeller(intent.name, intent.password).onEach {
                        it.onSuccess {
                            sideEffect.emit(AddSellerContract.SideEffect.ShowSuccessMessage(it))
                            direction.back()
                        }
                        it.onFailure {
                            sideEffect.emit(AddSellerContract.SideEffect.ShowNotification(it.message.toString()))
                        }
                    }.launchIn(viewModelScope)
                }
            }

            AddSellerContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }


}