package com.vazch.mvvmtemplate.data

import com.vazch.mvvmtemplate.core.AppResult
import com.vazch.mvvmtemplate.data.database.dao.QuoteDao
import com.vazch.mvvmtemplate.data.database.entities.QuoteEntity
import com.vazch.mvvmtemplate.data.database.entities.toDatabase
import com.vazch.mvvmtemplate.data.model.QuoteModel
import com.vazch.mvvmtemplate.data.network.QuoteService
import com.vazch.mvvmtemplate.domain.model.QuoteItem
import com.vazch.mvvmtemplate.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao,
) {

    suspend fun refreshQuotesFromApi(): AppResult<Unit> {
        return when (val res = api.getQuotes()) {
            is AppResult.Success -> {
                val items = res.data.map(QuoteModel::toDomain)
                quoteDao.insertAll(items.map { it.toDatabase() })
                AppResult.Success(Unit)
            }
            is AppResult.Error -> res
        }
    }

    //DB (observable and one-shot)
    fun observeAllQuotes(): Flow<List<QuoteItem>> =
        quoteDao.observeAllQuotes().map { list -> list.map { it.toDomain() } }

    suspend fun getAllQuotesOnce(): List<QuoteItem> =
        quoteDao.getAllQuotesOnce().map { it.toDomain() }

    suspend fun insertQuotesToDatabase(quotes: List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.clearAll()
    }
}