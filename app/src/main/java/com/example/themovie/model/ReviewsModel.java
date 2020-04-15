package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReviewsModel implements Serializable {

    @SerializedName("author")
    @Expose
    private String authorReviews;

    @SerializedName("content")
    @Expose
    private String contentReviews;

    @SerializedName("id")
    @Expose
    private String idReviews;

    @SerializedName("url")
    @Expose
    private String urlReviews;

    public ReviewsModel(String authorReviews, String contentReviews, String idReviews, String urlReviews){
        this.authorReviews = authorReviews;
        this.contentReviews = contentReviews;
        this.idReviews = idReviews;
        this.urlReviews = urlReviews;
    }

    public String getAuthorReviews() {
        return authorReviews;
    }

    public void setAuthorReviews(String authorReviews) {
        this.authorReviews = authorReviews;
    }

    public String getContentReviews() {
        return contentReviews;
    }

    public void setContentReviews(String contentReviews) {
        this.contentReviews = contentReviews;
    }

    public String getIdReviews() {
        return idReviews;
    }

    public void setIdReviews(String idReviews) {
        this.idReviews = idReviews;
    }

    public String getUrlReviews() {
        return urlReviews;
    }

    public void setUrlReviews(String urlReviews) {
        this.urlReviews = urlReviews;
    }
}
