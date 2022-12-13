package com.example.news.service;

import com.example.news.model.api.Article;
import com.example.news.model.api.ArticleSource;
import com.example.news.model.api.NewsRES;
import com.example.news.model.bo.ArticleBo;
import com.example.news.model.bo.ArticleSourceBo;
import com.example.news.model.bo.NewsBo;
import com.example.news.service.search.NewsSearchService;
import com.example.news.util.mapper.NewsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

class NewsServiceImplTest {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String DESCRIPTION = "description";
    private static final String URL = "URL";
    private static final LocalDateTime PUBLISHED_AT = LocalDateTime.now();
    private static final String SOURCE_NAME = "Source Name";
    private static final String SOURCE_URL = "Source URL";

    @Mock
    NewsMapper newsMapper;
    @Mock
    NewsSearchService newsSearchService;
    @InjectMocks
    NewsServiceImpl newsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchNews_whenInputsAreGiven_thenReturnNewsResponse() {
        NewsBo mockNewsBo = createNewsBo();
        NewsRES mockNewsRES = createNewsRES();
        Mockito.when(newsSearchService.search(anyString(), anyBoolean(), anyInt())).thenReturn(mockNewsBo);
        Mockito.when(newsMapper.convert(any(NewsBo.class))).thenReturn(mockNewsRES);
        NewsRES newsRES = newsService.searchNews(anyString(), anyBoolean(), anyInt());
        assertEquals(mockNewsBo.getTotalArticles(), newsRES.getNumberOfArticles());
        assertEquals(mockNewsBo.getArticles().get(0).getTitle(), newsRES.getData().get(0).getTitle());
        assertEquals(mockNewsBo.getArticles().get(0).getContent(), newsRES.getData().get(0).getContent());
        assertEquals(mockNewsBo.getArticles().get(0).getDescription(), newsRES.getData().get(0).getDescription());
        assertEquals(mockNewsBo.getArticles().get(0).getUrl(), newsRES.getData().get(0).getUrl());
        assertEquals(mockNewsBo.getArticles().get(0).getPublishedAt(), newsRES.getData().get(0).getPublishedAt());
        assertEquals(mockNewsBo.getArticles().get(0).getSource().getName(),
                newsRES.getData().get(0).getSource().getName());
        assertEquals(mockNewsBo.getArticles().get(0).getSource().getUrl(),
                newsRES.getData().get(0).getSource().getUrl());
    }

    private NewsRES createNewsRES() {
        NewsRES newsRES = new NewsRES();
        newsRES.setNumberOfArticles(1);
        newsRES.setData(createArticleList());
        return newsRES;
    }

    private List<Article> createArticleList() {
        Article article = new Article();
        article.setTitle(TITLE);
        article.setContent(CONTENT);
        article.setDescription(DESCRIPTION);
        article.setUrl(URL);
        article.setPublishedAt(PUBLISHED_AT);
        article.setSource(createArticleSource());
        return Collections.singletonList(article);
    }

    private ArticleSource createArticleSource() {
        ArticleSource articleSource = new ArticleSource();
        articleSource.setName(SOURCE_NAME);
        articleSource.setUrl(SOURCE_URL);
        return articleSource;
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