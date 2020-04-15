package com.example.themovie.api;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface BaseApiInterface {

    @GET("genre/movie/list")
    Call<ResponseBody> getMovieGenre(@QueryMap HashMap<String, String> params);

    @GET("discover/movie")
    Call<ResponseBody> getMovies(@QueryMap HashMap<String, String> params);

    @GET("movie/{movie_id}")
    Call<ResponseBody> getMovieDetail(@Path("movie_id") String movieId, @QueryMap HashMap<String, String> params);

    @GET("movie/{movie_id}/reviews")
    Call<ResponseBody> getMovieReview(@Path("movie_id") String movieId, @QueryMap HashMap<String, String> params);

    @GET("movie/{movie_id}/videos")
    Call<ResponseBody> getMovieTrailer(@Path("movie_id") String movieId, @QueryMap HashMap<String, String> params);
}
