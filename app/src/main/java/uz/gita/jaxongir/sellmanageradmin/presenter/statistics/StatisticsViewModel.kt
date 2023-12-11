package uz.gita.jaxongir.sellmanageradmin.presenter.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val repository: AppRepository,
) : ViewModel(), StatisticsContract.ViewModel {
    private val _uiState = MutableStateFlow(StatisticsContract.UIState())
    override val uiState: StateFlow<StatisticsContract.UIState>
        get() = _uiState

//    init {
//        loadStatistics()
//    }

//    private fun loadStatistics() {
//        viewModelScope.launch {
//            val startDate = getFormattedDateForLast24Hours()
//            val sellerSalesMap = repository.getSelledProducts(startDate).first()
//
////            _uiState.value = StatisticsContract.UIState(name = "", sellerSalesMap = sellerSalesMap)
//        }
//    }
//
//    private fun getFormattedDateForLast24Hours(): String {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.HOUR_OF_DAY, -24)
//        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
//    }

    override fun onEventDispatcher(intent: StatisticsContract.Intent) {
        when (intent) {
            is StatisticsContract.Intent.Name -> {
                repository.getSoldProducts(intent.name).onEach {
                    _uiState.update { uiState ->
                        uiState.copy(ls = it)
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}