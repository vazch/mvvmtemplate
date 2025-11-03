package com.vazch.mvvmtemplate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vazch.mvvmtemplate.domain.GetAllQuotesUseCase
import com.vazch.mvvmtemplate.domain.GetRandomQuoteUseCase
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val getAllQuotesUseCase: GetAllQuotesUseCase
): ViewModel() {
    // live data
    val quoteItemLiveData = MutableLiveData<QuoteItem>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    fun randomQuote() {
        viewModelScope.launch {
            isLoadingLiveData.postValue(true)
            val result = getRandomQuoteUseCase()
            quoteItemLiveData.postValue(result)
            isLoadingLiveData.postValue(false)
        }
    }

    fun onCreate() {
        viewModelScope.launch {
            isLoadingLiveData.postValue(true)
            val result = getAllQuotesUseCase()
            if(result.isNotEmpty()){
                quoteItemLiveData.postValue(result.random())
                isLoadingLiveData.postValue(false)
            }
        }
    }

}
