package com.vazch.mvvmtemplate.data.network

import com.vazch.mvvmtemplate.core.RetrofitHelper
import com.vazch.mvvmtemplate.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api: QuoteApiClient
){
    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            val body = response.body()
            body ?: emptyList()
        }
    }
}