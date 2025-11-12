package com.vazch.mvvmtemplate.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vazch.mvvmtemplate.data.database.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    // Observable stream: emits a new list whenever quote_table changes
    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    fun observeAllQuotes(): Flow<List<QuoteEntity>>

    // One-Shot snapshot (handy for things like 'pick a random now' )
    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    suspend fun getAllQuotesOnce(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<QuoteEntity>)

    @Query("DELETE FROM QUOTE_TABLE")
    suspend fun clearAll()
}