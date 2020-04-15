package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductionCountriesList implements Serializable {

    @SerializedName("iso_3166_1")
    @Expose
    private String ISOProduction;

    @SerializedName("name")
    @Expose
    private String nameCountry;

    public ProductionCountriesList(String ISOProduction, String nameCountry){
        this.ISOProduction = ISOProduction;
        this.nameCountry = nameCountry;
    }

    public String getISOProduction() {
        return ISOProduction;
    }

    public void setISOProduction(String ISOProduction) {
        this.ISOProduction = ISOProduction;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}
