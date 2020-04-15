package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoviesTrailerModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String idMovTrailer;

    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;

    @SerializedName("iso_3166_1")
    @Expose
    private String ISO31661;

    @SerializedName("key")
    @Expose
    private String keyMoviesTrailer;

    @SerializedName("name")
    @Expose
    private String nameMoviesTrailer;

    @SerializedName("site")
    @Expose
    private String siteMoviesTrailer;

    @SerializedName("size")
    @Expose
    private String sizeMoviesTrailer;

    @SerializedName("type")
    @Expose
    private String typeMoviesTrailer;

    public MoviesTrailerModel(String idMovTrailer, String iso6391, String ISO31661, String keyMoviesTrailer, String nameMoviesTrailer, String siteMoviesTrailer,
                              String sizeMoviesTrailer, String typeMoviesTrailer){
        this.idMovTrailer = idMovTrailer;
        this.iso6391 = iso6391;
        this.ISO31661 = ISO31661;
        this.keyMoviesTrailer = keyMoviesTrailer;
        this.nameMoviesTrailer = nameMoviesTrailer;
        this.siteMoviesTrailer = siteMoviesTrailer;
        this.sizeMoviesTrailer = sizeMoviesTrailer;
        this.typeMoviesTrailer = typeMoviesTrailer;
    }

    public String getIdMovTrailer() {
        return idMovTrailer;
    }

    public void setIdMovTrailer(String idMovTrailer) {
        this.idMovTrailer = idMovTrailer;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getISO31661() {
        return ISO31661;
    }

    public void setISO31661(String ISO31661) {
        this.ISO31661 = ISO31661;
    }

    public String getKeyMoviesTrailer() {
        return keyMoviesTrailer;
    }

    public void setKeyMoviesTrailer(String keyMoviesTrailer) {
        this.keyMoviesTrailer = keyMoviesTrailer;
    }

    public String getNameMoviesTrailer() {
        return nameMoviesTrailer;
    }

    public void setNameMoviesTrailer(String nameMoviesTrailer) {
        this.nameMoviesTrailer = nameMoviesTrailer;
    }

    public String getSiteMoviesTrailer() {
        return siteMoviesTrailer;
    }

    public void setSiteMoviesTrailer(String siteMoviesTrailer) {
        this.siteMoviesTrailer = siteMoviesTrailer;
    }

    public String getSizeMoviesTrailer() {
        return sizeMoviesTrailer;
    }

    public void setSizeMoviesTrailer(String sizeMoviesTrailer) {
        this.sizeMoviesTrailer = sizeMoviesTrailer;
    }

    public String getTypeMoviesTrailer() {
        return typeMoviesTrailer;
    }

    public void setTypeMoviesTrailer(String typeMoviesTrailer) {
        this.typeMoviesTrailer = typeMoviesTrailer;
    }
}
