package com.example.themovie.api;

public class APIUrl {
    // ini adalah IP localhost.
    public static final String BASE_URL_API = "https://api.themoviedb.org/3/";

    public static BaseApiInterface getAPIService(){
        return APIClient.getClient(BASE_URL_API).create(BaseApiInterface.class);
    }
}
