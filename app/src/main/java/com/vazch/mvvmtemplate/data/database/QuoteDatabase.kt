package com.vazch.mvvmtemplate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vazch.mvvmtemplate.data.database.dao.QuoteDao
import com.vazch.mvvmtemplate.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
}