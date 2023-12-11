package uz.gita.jaxongir.sellmanageradmin.presenter.invalid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class ExpiredViewModel @Inject constructor(
    private val repository: AppRepository,
    private val direction: ExpiredDirection
) : ExpiredContract.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(ExpiredContract.UIState())

    init {
        viewModelScope.launch {
            repository.getAllProducts()
                .onCompletion { uiState.update { it.copy(isLoading = false) } }
                .onStart { uiState.update { it.copy(isLoading = true) } }
                .onEach {
                    it.onSuccess {
                        uiState.update { uiSatate ->
                            uiSatate.copy(productList = it.filter { !it.isValid })
                        }
                    }
                }.launchIn(viewModelScope)

        }
    }


    override fun onEventDispatcher(intent: ExpiredContract.Intent) {
        when(intent){
            ExpiredContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }

}