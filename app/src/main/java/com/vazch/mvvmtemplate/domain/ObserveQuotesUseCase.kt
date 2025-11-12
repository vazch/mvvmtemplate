package com.vazch.mvvmtemplate.domain

import com.vazch.mvvmtemplate.data.QuoteRepository
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    operator fun invoke(): Flow<List<QuoteItem>> = repository.observeAllQuotes()
}