package com.vazch.mvvmtemplate.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vazch.mvvmtemplate.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    suspend fun getAllQuote(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<QuoteEntity>)

    @Query("DELETE FROM QUOTE_TABLE")
    suspend fun clearAll()
}