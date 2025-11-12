package com.vazch.mvvmtemplate.data.network

import com.vazch.mvvmtemplate.core.AppResult
import com.vazch.mvvmtemplate.core.safeApiCall
import com.vazch.mvvmtemplate.data.model.QuoteModel
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api: QuoteApiClient
){
    suspend fun getQuotes(): AppResult<List<QuoteModel>> = safeApiCall { api.getAllQuotes() }
}