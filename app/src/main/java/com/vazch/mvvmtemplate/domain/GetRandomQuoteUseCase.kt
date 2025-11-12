package com.vazch.mvvmtemplate.domain

import com.vazch.mvvmtemplate.data.QuoteRepository
import com.vazch.mvvmtemplate.data.model.QuoteModel
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository,
) {
    suspend operator fun invoke(): QuoteItem? {
        val quotes = repository.getAllQuotesOnce()
        return quotes.randomOrNull()
    }
}