package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductionCompaniesModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String idProdCompanies;

    @SerializedName("logo_path")
    @Expose
    private String logoPath;

    @SerializedName("name")
    @Expose
    private String nameCompanies;

    @SerializedName("origin_country")
    @Expose
    private String originCountry;

    public ProductionCompaniesModel(String idProdCompanies, String logoPath, String nameCompanies, String originCountry){
        this.idProdCompanies = idProdCompanies;
        this.logoPath = logoPath;
        this.nameCompanies = nameCompanies;
        this.originCountry = originCountry;
    }

    public String getIdProdCompanies() {
        return idProdCompanies;
    }

    public void setIdProdCompanies(String idProdCompanies) {
        this.idProdCompanies = idProdCompanies;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getNameCompanies() {
        return nameCompanies;
    }

    public void setNameCompanies(String nameCompanies) {
        this.nameCompanies = nameCompanies;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
