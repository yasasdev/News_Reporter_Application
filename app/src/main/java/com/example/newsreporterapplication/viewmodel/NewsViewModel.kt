package com.example.newsreporterapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreporterapplication.model.News
import com.example.newsreporterapplication.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(app: Application, private val newsRepository: NewsRepository): AndroidViewModel(app) {

    fun addNews(news: News) =
        viewModelScope.launch {
            newsRepository.insertNews(news)
        }

    fun deleteNews(news: News) =
        viewModelScope.launch {
            newsRepository.deleteNews(news)
        }

    fun updateNews(news: News) =
        viewModelScope.launch {
            newsRepository.updateNews(news)
        }

    fun getAllNews() = newsRepository.getAllNews()

    fun searchNews(query: String?) =
        newsRepository.searchNews(query)

}