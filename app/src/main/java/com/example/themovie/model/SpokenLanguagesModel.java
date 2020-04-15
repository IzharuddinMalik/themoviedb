package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpokenLanguagesModel implements Serializable {

    @SerializedName("iso_639_1")
    @Expose
    private String ISOLanguages;

    @SerializedName("name")
    @Expose
    private String nameSpokenLang;

    public SpokenLanguagesModel(String ISOLanguages, String nameSpokenLang){
        this.ISOLanguages = ISOLanguages;
        this.nameSpokenLang = nameSpokenLang;
    }

    public String getISOLanguages() {
        return ISOLanguages;
    }

    public void setISOLanguages(String ISOLanguages) {
        this.ISOLanguages = ISOLanguages;
    }

    public String getNameSpokenLang() {
        return nameSpokenLang;
    }

    public void setNameSpokenLang(String nameSpokenLang) {
        this.nameSpokenLang = nameSpokenLang;
    }
}
