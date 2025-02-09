package com.example.newsreporterapplication.repository

import com.example.newsreporterapplication.database.NewsDatabase
import com.example.newsreporterapplication.model.News

class NewsRepository(private val  db: NewsDatabase) {

    suspend fun insertNews(news: News) = db.getNewsDao().insertNews(news)
    suspend fun deleteNews(news: News) = db.getNewsDao().deleteNews(news)
    suspend fun updateNews(news: News) = db.getNewsDao().updateNews(news)

    fun getAllNews() = db.getNewsDao().getAllNews()
    fun searchNews(query: String?) = db.getNewsDao().searchNews(query)

}