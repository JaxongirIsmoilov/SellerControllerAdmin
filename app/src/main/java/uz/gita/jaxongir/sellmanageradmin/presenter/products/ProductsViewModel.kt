package uz.gita.jaxongir.sellmanageradmin.presenter.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val direction: ProductsDirection,
    private val repository: AppRepository
) : ViewModel(), ProductsContract.ViewModel {
    override val uiState = MutableStateFlow(ProductsContract.UIState())

    init {
        loadData()
    }

    override fun onEventDispatcher(intent: ProductsContract.Intent) {
        when (intent) {
            ProductsContract.Intent.Load -> {
                loadData()
            }

            ProductsContract.Intent.MoveToAddScreen -> {
                viewModelScope.launch {
                    direction.moveToAddProductScreen()
                }
            }
        }
    }

    private fun loadData() {
        repository.getAllProducts().onEach {
            it.onSuccess {
                uiState.update { uiState ->
                    uiState.copy(ls = it)
                }
            }
            it.onFailure {
                throw Exception(it)
            }
        }.launchIn(viewModelScope)
    }
}