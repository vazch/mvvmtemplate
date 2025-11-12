package com.vazch.mvvmtemplate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vazch.mvvmtemplate.core.AppResult
import com.vazch.mvvmtemplate.domain.ObserveQuotesUseCase
import com.vazch.mvvmtemplate.domain.RefreshQuotesUseCase
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UiState(
    val isLoading: Boolean = false,
    val items: List<QuoteItem> = emptyList(),
    val current: QuoteItem? = null,
    val error: String? = null
)

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val observeQuotesUseCase: ObserveQuotesUseCase,
    private val refreshQuotesUseCase: RefreshQuotesUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // 1) Observe DB continuously
        viewModelScope.launch {
            observeQuotesUseCase().collect { list ->
                _uiState.update { st ->
                    val newCurrent = st.current ?: list.firstOrNull()
                    st.copy(isLoading = false, items = list, current = newCurrent, error = null)
                }
            }
        }
        // 2) Trigger a refresh (network -> DB). Errors go into UiState.error
        viewModelScope.launch {
            when (val res = refreshQuotesUseCase()) {
                is AppResult.Success -> Unit
                is AppResult.Error -> _uiState.update { it.copy(error = res.message, isLoading = false) }
            }
        }
    }

    fun randomQuote() {
        val list = _uiState.value.items
        if (list.isNotEmpty()) {
            _uiState.update { it.copy(current = list.random(), error = null) }
        } else {
            _uiState.update { it.copy(error = "No quotes available") }
        }
    }

}
