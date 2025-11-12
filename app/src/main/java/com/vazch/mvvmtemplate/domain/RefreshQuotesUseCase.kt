package com.vazch.mvvmtemplate.domain

import com.vazch.mvvmtemplate.core.AppResult
import com.vazch.mvvmtemplate.data.QuoteRepository
import javax.inject.Inject

class RefreshQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): AppResult<Unit> = repository.refreshQuotesFromApi()
}
