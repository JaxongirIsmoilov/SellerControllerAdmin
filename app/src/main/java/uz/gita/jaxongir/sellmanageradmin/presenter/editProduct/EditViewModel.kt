package uz.gita.jaxongir.sellmanageradmin.presenter.editProduct

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
    private val direction: EditProductDirection,
    private val repository: AppRepository
) : ViewModel(), EditProductContract.ViewModel {
    override val sideEffect =
        MutableStateFlow<EditProductContract.SideEffect>(EditProductContract.SideEffect.Init)

    override fun eventDispatchers(intent: EditProductContract.Intent) {
        when (intent) {
            EditProductContract.Intent.MoveToProductsScreen -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is EditProductContract.Intent.EditSeller -> {
                viewModelScope.launch {
                    repository.editProduct(intent.productData).onEach {
                        it.onSuccess {
                            sideEffect.emit(EditProductContract.SideEffect.ShowToast(it))
                        }
                        it.onFailure {
                            sideEffect.emit(EditProductContract.SideEffect.ShowToast(it.message.toString()))
                        }
                    }.launchIn(viewModelScope)
                    direction.back()
                }
            }
        }
    }

}
