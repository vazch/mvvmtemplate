package com.vazch.mvvmtemplate.data

import com.vazch.mvvmtemplate.data.database.dao.QuoteDao
import com.vazch.mvvmtemplate.data.database.entities.QuoteEntity
import com.vazch.mvvmtemplate.data.network.QuoteService
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import com.vazch.mvvmtemplate.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao,
) {
    suspend fun getAllQuotesFromApi(): List<QuoteItem> {
        val response = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<QuoteItem>{
        val response = quoteDao.getAllQuote()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotesToDatabase(quotes: List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.clearAll()
    }
}