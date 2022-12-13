package com.example.news.service.search;

import com.example.news.model.bo.ArticleBo;
import com.example.news.model.bo.ArticleSourceBo;
import com.example.news.model.bo.NewsBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class NewsSearchServiceImplTest {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String DESCRIPTION = "description";
    private static final String URL = "URL";
    private static final LocalDateTime PUBLISHED_AT = LocalDateTime.now();
    private static final String SOURCE_NAME = "Source Name";
    private static final String SOURCE_URL = "Source URL";

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    NewsSearchServiceImpl newsSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void search_whenInputParamsAreGiven_thenReturnFoundNews() {
        NewsBo mockNewsBo = createNewsBo();
        ResponseEntity<NewsBo> newsBoResponseEntity = new ResponseEntity<>(mockNewsBo, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(anyString(), eq(NewsBo.class))).thenReturn(newsBoResponseEntity);
        NewsBo newsBo = newsSearchService.search("", false, 1);
        assertEquals(mockNewsBo.getTotalArticles(), newsBo.getTotalArticles());
        assertEquals(mockNewsBo.getArticles().get(0).getTitle(), newsBo.getArticles().get(0).getTitle());
        assertEquals(mockNewsBo.getArticles().get(0).getContent(), newsBo.getArticles().get(0).getContent());
        assertEquals(mockNewsBo.getArticles().get(0).getDescription(), newsBo.getArticles().get(0).getDescription());
        assertEquals(mockNewsBo.getArticles().get(0).getUrl(), newsBo.getArticles().get(0).getUrl());
        assertEquals(mockNewsBo.getArticles().get(0).getPublishedAt(), newsBo.getArticles().get(0).getPublishedAt());
        assertEquals(mockNewsBo.getArticles().get(0).getSource().getName(),
                newsBo.getArticles().get(0).getSource().getName());
        assertEquals(mockNewsBo.getArticles().get(0).getSource().getUrl(),
                newsBo.getArticles().get(0).getSource().getUrl());
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