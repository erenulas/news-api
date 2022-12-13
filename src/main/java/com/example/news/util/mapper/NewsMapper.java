package com.example.news.util.mapper;

import com.example.news.model.bo.NewsBo;
import com.example.news.model.api.NewsRES;

public interface NewsMapper {
    NewsRES convert(NewsBo newsBo);
}
