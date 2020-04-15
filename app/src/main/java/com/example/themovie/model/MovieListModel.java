package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieListModel implements Serializable {

    @SerializedName("popularity")
    @Expose
    private String popularityMovies;

    @SerializedName("id")
    @Expose
    private String idMoviesList;

    @SerializedName("video")
    @Expose
    private String videoMoviesList;

    @SerializedName("vote_count")
    @Expose
    private String jumlahVote;

    @SerializedName("vote_average")
    @Expose
    private String voteAverage;

    @SerializedName("title")
    @Expose
    private String titleMoviesList;

    @SerializedName("release_date")
    @Expose
    private String releaseDateMoviesList;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("genre_ids")
    @Expose
    private String genreId;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("adult")
    @Expose
    private String adultMoviesList;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public MovieListModel(String popularityMovies, String idMoviesList, String videoMoviesList, String jumlahVote, String voteAverage, String titleMoviesList, String releaseDateMoviesList,
                          String originalLanguage, String originalTitle, String genreId, String backdropPath, String adultMoviesList, String overview, String posterPath){
        this.popularityMovies = popularityMovies;
        this.idMoviesList = idMoviesList;
        this.videoMoviesList = videoMoviesList;
        this.jumlahVote = jumlahVote;
        this.voteAverage = voteAverage;
        this.titleMoviesList = titleMoviesList;
        this.releaseDateMoviesList = releaseDateMoviesList;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreId = genreId;
        this.backdropPath = backdropPath;
        this.adultMoviesList = adultMoviesList;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public String getPopularityMovies() {
        return popularityMovies;
    }

    public void setPopularityMovies(String popularityMovies) {
        this.popularityMovies = popularityMovies;
    }

    public String getIdMoviesList() {
        return idMoviesList;
    }

    public void setIdMoviesList(String idMoviesList) {
        this.idMoviesList = idMoviesList;
    }

    public String getVideoMoviesList() {
        return videoMoviesList;
    }

    public void setVideoMoviesList(String videoMoviesList) {
        this.videoMoviesList = videoMoviesList;
    }

    public String getJumlahVote() {
        return jumlahVote;
    }

    public void setJumlahVote(String jumlahVote) {
        this.jumlahVote = jumlahVote;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitleMoviesList() {
        return titleMoviesList;
    }

    public void setTitleMoviesList(String titleMoviesList) {
        this.titleMoviesList = titleMoviesList;
    }

    public String getReleaseDateMoviesList() {
        return releaseDateMoviesList;
    }

    public void setReleaseDateMoviesList(String releaseDateMoviesList) {
        this.releaseDateMoviesList = releaseDateMoviesList;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getAdultMoviesList() {
        return adultMoviesList;
    }

    public void setAdultMoviesList(String adultMoviesList) {
        this.adultMoviesList = adultMoviesList;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
