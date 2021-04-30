package com.example.cmpe277_pocketnews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsList {
    private String status;
    private int totalResult;

    @SerializedName("articles")
    @Expose
    private List<ArticleList> article;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }
    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<ArticleList> getArticle() {
        return article;
    }
    public void setArticle(List<ArticleList> article) {
        this.article = article;
    }
}
