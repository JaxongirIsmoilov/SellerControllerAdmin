package uz.gita.jaxongir.sellmanageradmin.presenter.products

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import uz.gita.jaxongir.sellmanageradmin.utills.myLog
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val direction: ProductsDirection,
    private val repository: AppRepository,
    @ApplicationContext context: Context
) : ViewModel(), ProductsContract.ViewModel {
    override val uiState = MutableStateFlow(ProductsContract.UIState())

    init {
        viewModelScope.launch {
            repository.scheduleClearingList(context)
        }
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

            is ProductsContract.Intent.MoveToEditScreen -> {
                viewModelScope.launch {
                    direction.moveToEditScreen(intent.productData)
                }
            }

            is ProductsContract.Intent.MakeExpireProduct -> {
                repository.makeInvalid(intent.productData).onEach {

                }.launchIn(viewModelScope)
            }
        }
    }

    private fun loadData() {
        repository.getAllProducts().onEach {
            it.onSuccess {
                myLog("Size : ${it.size}")
                uiState.update { uiState ->
                    uiState.copy(ls = it.filter { it.isValid })
                }
            }
            it.onFailure {
                throw Exception(it)
            }
        }.launchIn(viewModelScope)
    }
}