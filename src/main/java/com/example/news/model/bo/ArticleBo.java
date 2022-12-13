package com.example.news.model.bo;

import java.time.LocalDateTime;

public class ArticleBo {
    private String title;
    private String description;
    private String content;
    private String url;
    private LocalDateTime publishedAt;
    private ArticleSourceBo source;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public ArticleSourceBo getSource() {
        return source;
    }

    public void setSource(ArticleSourceBo source) {
        this.source = source;
    }

}
