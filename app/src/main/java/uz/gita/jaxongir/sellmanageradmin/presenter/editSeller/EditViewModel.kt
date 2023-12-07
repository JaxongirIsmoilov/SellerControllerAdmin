package uz.gita.jaxongir.sellmanageradmin.presenter.editSeller

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
class EditViewModel @Inject constructor(
    private val direction: EditDirection,
    private val repository: AppRepository
) : ViewModel(), EditContract.ViewModel {

    override val sideEffect =
        MutableStateFlow<EditContract.SideEffect>(EditContract.SideEffect.Init)

    override fun eventDispatchers(intent: EditContract.Intent) {
        when (intent) {
            EditContract.Intent.MoveToSellerScreen -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is EditContract.Intent.EditSeller -> {

                viewModelScope.launch {
                    repository.editSeller(
                        intent.sellerData
                    ).onEach {
                        it.onSuccess {
                            sideEffect.emit(EditContract.SideEffect.ShowNotification(it))
                        }
                    }.launchIn(viewModelScope)
                }

            }
        }
    }
}