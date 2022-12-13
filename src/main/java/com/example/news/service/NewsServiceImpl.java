package com.example.news.service;

import com.example.news.model.bo.NewsBo;
import com.example.news.model.api.NewsRES;
import com.example.news.service.search.NewsSearchService;
import com.example.news.util.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;
    @Autowired
    NewsSearchService searchService;

    @Override
    public NewsRES searchNews(String query, boolean searchByTitle, int size) {
        NewsBo newsBo = searchService.search(query, searchByTitle, size);
        return newsMapper.convert(newsBo);
    }

}
