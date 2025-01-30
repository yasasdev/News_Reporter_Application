package com.example.newsreporterapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newsreporterapplication.model.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: News)

    @Update
    suspend fun updateNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)

    @Query("SELECT * FROM NEWS ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<News>>

    @Query("SELECT * FROM NEWS WHERE newsTitle LIKE :query OR newsDescription LIKE :query")
    fun searchNote(query: String?): LiveData<List<News>>
}