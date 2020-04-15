package com.example.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GenreModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String idGenre;

    @SerializedName("name")
    @Expose
    private String nameGenre;

    public GenreModel(String idGenre, String nameGenre){
        this.idGenre = idGenre;
        this.nameGenre = nameGenre;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }
}
