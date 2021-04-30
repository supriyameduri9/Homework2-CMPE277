package com.example.cmpe277_pocketnews.Models;
public class ArticleList {
    private SourceList source;
    private String author,title,description,url,urlToImage,publishedAt;

    public String getUrlToImage() { return urlToImage;}
    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage;}

    public SourceList getSource() {
        return source;
    }
    public void setSource(SourceList source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
