package uz.gita.jaxongir.sellmanageradmin.presenter.seller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class SellerViewModel @Inject constructor(
    private val direction: SellerDirection,
    private val repository: AppRepository
) : ViewModel(), SellerScreenContract.ViewModel {
    override val uiState = MutableStateFlow(SellerScreenContract.UIState())

    init {
        loadData()
    }
    override fun onEventDispatcher(intent: SellerScreenContract.Intent) {
        when(intent){
            SellerScreenContract.Intent.MoveToAdd -> {
                viewModelScope.launch {
                    direction.moveToAddScreen()
                }
            }

            SellerScreenContract.Intent.Load -> {
                loadData()
            }

            is SellerScreenContract.Intent.DeleteSeller -> {
                repository.deleteSeller(intent.data)
            }
            is SellerScreenContract.Intent.EditSeller -> {
                repository.editSeller(intent.data)
            }
            is SellerScreenContract.Intent.MoveToEditScreen -> {
                viewModelScope.launch {
                    direction.moveToEditScreen(intent.sellerData)
                }
            }
        }
    }

    private fun loadData(){
        repository.getAllUsers()
            .onCompletion { uiState.update { it.copy(isLoading = false) } }
            .onStart { uiState.update { it.copy(isLoading = true) } }
            .onEach {
                it.onSuccess { ls ->

                    uiState.update { it.copy(ls) }
                }
            }
    }

}