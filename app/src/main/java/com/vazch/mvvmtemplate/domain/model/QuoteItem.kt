package com.vazch.mvvmtemplate.domain.model

import com.vazch.mvvmtemplate.data.database.entities.QuoteEntity
import com.vazch.mvvmtemplate.data.model.QuoteModel

data class QuoteItem(
    val quote: String,
    val author: String,
)

fun QuoteEntity.toDomain() = QuoteItem(
    quote = quote,
    author = author,
)

fun QuoteModel.toDomain() = QuoteItem(
    quote = quote,
    author = author,
)