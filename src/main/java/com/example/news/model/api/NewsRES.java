package com.example.news.model.api;

import java.util.List;

public class NewsRES {

    private int numberOfArticles;
    private List<Article> data;

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

}
