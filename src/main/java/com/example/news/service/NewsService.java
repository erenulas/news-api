package com.example.news.service;

import com.example.news.model.api.NewsRES;

public interface NewsService {
    NewsRES searchNews(String query, boolean searchByTitle, int size);
}
