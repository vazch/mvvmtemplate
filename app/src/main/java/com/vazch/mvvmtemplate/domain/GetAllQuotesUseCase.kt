package com.vazch.mvvmtemplate.domain

import com.vazch.mvvmtemplate.data.QuoteRepository
import com.vazch.mvvmtemplate.data.database.entities.toDatabase
import com.vazch.mvvmtemplate.data.model.QuoteModel
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import javax.inject.Inject

class GetAllQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): List<QuoteItem> {
        val quotes = repository.getAllQuotesFromApi()
        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotesToDatabase(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }

}