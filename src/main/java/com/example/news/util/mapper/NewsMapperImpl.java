package com.example.news.util.mapper;

import com.example.news.model.api.Article;
import com.example.news.model.api.ArticleSource;
import com.example.news.model.api.NewsRES;
import com.example.news.model.bo.ArticleBo;
import com.example.news.model.bo.ArticleSourceBo;
import com.example.news.model.bo.NewsBo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapperImpl implements NewsMapper {
    @Override
    public NewsRES convert(NewsBo newsBo) {
        NewsRES newsRES = new NewsRES();
        newsRES.setNumberOfArticles(newsBo.getTotalArticles());
        newsRES.setData(convertArticleBoListToArticleList(newsBo.getArticles()));
        return newsRES;
    }

    private List<Article> convertArticleBoListToArticleList(List<ArticleBo> articleBoList) {
        List<Article> articles = new ArrayList<>();
        for (ArticleBo articleBo : articleBoList) {
            articles.add(convertArticleBoToArticle(articleBo));
        }
        return articles;
    }

    private Article convertArticleBoToArticle(ArticleBo articleBo) {
        Article article = new Article();
        article.setTitle(articleBo.getTitle());
        article.setDescription(articleBo.getDescription());
        article.setContent(articleBo.getContent());
        article.setUrl(articleBo.getUrl());
        article.setPublishedAt(articleBo.getPublishedAt());
        article.setSource(convertSourceBoToSource(articleBo.getSource()));
        return article;
    }

    private ArticleSource convertSourceBoToSource(ArticleSourceBo articleSourceBo) {
        ArticleSource articleSource = new ArticleSource();
        articleSource.setName(articleSourceBo.getName());
        articleSource.setUrl(articleSourceBo.getUrl());
        return articleSource;
    }
}
