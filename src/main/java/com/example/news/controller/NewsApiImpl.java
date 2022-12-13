package com.example.news.controller;

import com.example.news.model.api.NewsRES;
import com.example.news.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsApiImpl implements NewsApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsApiImpl.class);

    @Autowired
    NewsService newsService;

    @Override
    public ResponseEntity<NewsRES> searchNews(String query, boolean searchByTitle, int size) {
        LOGGER.info("A search request is received for the following params: {}, {}, {}", query, searchByTitle, size);
        NewsRES newsRES = newsService.searchNews(query, searchByTitle, size);
        ResponseEntity<NewsRES> response = new ResponseEntity<>(newsRES, HttpStatus.OK);
        return response;
    }
}
