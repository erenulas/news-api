package com.example.news.model.bo;

import java.util.ArrayList;
import java.util.List;

public class NewsBo {
    private int totalArticles;
    private List<ArticleBo> articles = new ArrayList<>();

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<ArticleBo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBo> articles) {
        this.articles = articles;
    }
}
