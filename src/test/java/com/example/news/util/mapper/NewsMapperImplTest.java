package com.example.news.util.mapper;

import com.example.news.model.api.NewsRES;
import com.example.news.model.bo.ArticleBo;
import com.example.news.model.bo.ArticleSourceBo;
import com.example.news.model.bo.NewsBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsMapperImplTest {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String DESCRIPTION = "description";
    private static final String URL = "URL";
    private static final LocalDateTime PUBLISHED_AT = LocalDateTime.now();
    private static final String SOURCE_NAME = "Source Name";
    private static final String SOURCE_URL = "Source URL";


    private NewsMapperImpl newsMapper = new NewsMapperImpl();

    @Test
    void convert_whenNewsBoIsNotNull_ReturnNewsResponse() {
        NewsBo newsBo = createNewsBo();
        NewsRES newsRES = newsMapper.convert(newsBo);
        assertEquals(newsBo.getTotalArticles(), newsRES.getNumberOfArticles());
        assertEquals(newsBo.getArticles().get(0).getTitle(), newsRES.getData().get(0).getTitle());
        assertEquals(newsBo.getArticles().get(0).getContent(), newsRES.getData().get(0).getContent());
        assertEquals(newsBo.getArticles().get(0).getDescription(), newsRES.getData().get(0).getDescription());
        assertEquals(newsBo.getArticles().get(0).getUrl(), newsRES.getData().get(0).getUrl());
        assertEquals(newsBo.getArticles().get(0).getPublishedAt(), newsRES.getData().get(0).getPublishedAt());
        assertEquals(newsBo.getArticles().get(0).getSource().getName(), newsRES.getData().get(0).getSource().getName());
        assertEquals(newsBo.getArticles().get(0).getSource().getUrl(), newsRES.getData().get(0).getSource().getUrl());
    }

    private NewsBo createNewsBo() {
        NewsBo newsBo = new NewsBo();
        newsBo.setTotalArticles(1);
        newsBo.setArticles(createArticleBoList());
        return newsBo;
    }

    private List<ArticleBo> createArticleBoList() {
        ArticleBo articleBo = new ArticleBo();
        articleBo.setTitle(TITLE);
        articleBo.setContent(CONTENT);
        articleBo.setDescription(DESCRIPTION);
        articleBo.setUrl(URL);
        articleBo.setPublishedAt(PUBLISHED_AT);
        articleBo.setSource(createArticleSourceBo());
        return Collections.singletonList(articleBo);
    }

    private ArticleSourceBo createArticleSourceBo() {
        ArticleSourceBo articleSourceBo = new ArticleSourceBo();
        articleSourceBo.setName(SOURCE_NAME);
        articleSourceBo.setUrl(SOURCE_URL);
        return articleSourceBo;
    }
}