package com.example.themovie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themovie.R;
import com.example.themovie.adapter.AdapterGenreMovies;
import com.example.themovie.adapter.AdapterMovieTrailer;
import com.example.themovie.adapter.AdapterMoviesList;
import com.example.themovie.adapter.AdapterProductionCompanies;
import com.example.themovie.adapter.AdapterProductionCountries;
import com.example.themovie.adapter.AdapterReviewsModel;
import com.example.themovie.api.APIConnect;
import com.example.themovie.api.APIUrl;
import com.example.themovie.api.BaseApiInterface;
import com.example.themovie.model.GenreModel;
import com.example.themovie.model.MoviesTrailerModel;
import com.example.themovie.model.ProductionCompaniesModel;
import com.example.themovie.model.ProductionCountriesList;
import com.example.themovie.model.ReviewsModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovies extends AppCompatActivity {

    APIConnect mApiConnect;
    BaseApiInterface mApiInterface;
    List<ProductionCountriesList> prodCountriesList;
    List<ProductionCompaniesModel> prodCompaniesList;
    List<GenreModel> genreList;
    List<ReviewsModel> reviewsList;
    List<MoviesTrailerModel> moviesTrailerModelList;

    AdapterProductionCountries adapterProductionCountries;
    AdapterProductionCompanies adapterProductionCompanies;
    AdapterGenreMovies adapterGenreMovies;
    AdapterReviewsModel adapterReviewsModel;
    AdapterMovieTrailer adapterMovieTrailer;

    RecyclerView rvListProdCountries, rvListProdCompanies, rvListGenreMovie, rvListReviewMovie, rvListVideoTrailer;
    ImageView ivPosterMovies;
    TextView tvTitleMovies, tvOriginalTitle, tvPopularity, tvReleaseDate, tvVoteCount, tvVoteAverage, tvReviewKosong;

    String movieId;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        rvListProdCountries = findViewById(R.id.rvListProductionCountriesMoviesDetail);
        rvListProdCompanies = findViewById(R.id.rvListProductionCompaniesMovieDetail);
        rvListGenreMovie = findViewById(R.id.rvListGenreMoviesDetail);
        rvListReviewMovie = findViewById(R.id.rvListReviewsMoviesDetail);
//        rvListVideoTrailer = findViewById(R.id.listMovieTrailer);
        ivPosterMovies = findViewById(R.id.ivPosterMovieDetail);
        tvTitleMovies = findViewById(R.id.tvTitleMoviesDetail);
        tvOriginalTitle = findViewById(R.id.tvOriginalTitleMoviesDetail);
        tvPopularity = findViewById(R.id.tvPopularityMoviesDetail);
        tvReleaseDate = findViewById(R.id.tvReleaseDateMoviesDetail);
        tvVoteCount = findViewById(R.id.tvVoteMoviesDetail);
        tvVoteAverage = findViewById(R.id.tvVoteAvgMoviesDetail);
        tvReviewKosong = findViewById(R.id.tvReviewKosong);

        youTubePlayerView = findViewById(R.id.videoTrailer);
        getLifecycle().addObserver(youTubePlayerView);

        movieId = getIntent().getStringExtra("movieId");
        Log.i("MOVIESID", " === " + movieId);

        View toastsucces = getLayoutInflater().inflate(R.layout.toast_success, null);
        View toastinfo = getLayoutInflater().inflate(R.layout.toast_information, null);
        View toastfail = getLayoutInflater().inflate(R.layout.toast_failed, null);

        mApiConnect = new APIConnect(DetailMovies.this, toastsucces, toastfail, toastinfo);

        viewDetailMovies();
    }

    public void viewDetailMovies(){

        mApiConnect.showdialogloading();

        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", "92c884a51f746457056143b22b374aa0");
        params.put("language", "ID");

        genreList = new ArrayList<>();
        genreList.clear();

        prodCompaniesList = new ArrayList<>();
        prodCompaniesList.clear();

        prodCountriesList = new ArrayList<>();
        prodCountriesList.clear();

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("api_key", "92c884a51f746457056143b22b374aa0");
        header.put("language", "ID");

        mApiInterface = APIUrl.getAPIService();
        mApiInterface.getMovieDetail(movieId, params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{

                        mApiConnect.closeDialogloading();

                        String result = response.body().string();
                        JSONObject objResult = new JSONObject(result);

                        Log.i("RESULT", " === " + objResult);

                        Picasso.with(DetailMovies.this).load("https://api.themoviedb.org/3/discover/movie" + objResult.getString("poster_path")).into(ivPosterMovies);
                        tvTitleMovies.setText(objResult.getString("title"));
                        tvOriginalTitle.setText(objResult.getString("original_title"));
                        tvPopularity.setText(objResult.getString("popularity"));
                        tvReleaseDate.setText(objResult.getString("release_date"));
                        tvVoteCount.setText(objResult.getString("vote_count"));
                        tvVoteAverage.setText(objResult.getString("vote_average"));

                        JSONArray arrGenres = objResult.getJSONArray("genres");
                        for (int i = 0; i < arrGenres.length(); i++){
                            JSONObject objGenres = arrGenres.getJSONObject(i);

                            genreList.add(i, new GenreModel("" + objGenres.getString("id"), "" + objGenres.getString("name")));
                        }

                        adapterGenreMovies = new AdapterGenreMovies(DetailMovies.this, genreList);
                        final RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(DetailMovies.this, LinearLayoutManager.HORIZONTAL, false);
                        rvListGenreMovie.setLayoutManager(mLayoutManager3);
                        rvListGenreMovie.setItemAnimator(new DefaultItemAnimator());
                        rvListGenreMovie.setItemViewCacheSize(genreList.size());
                        rvListGenreMovie.setDrawingCacheEnabled(true);
                        rvListGenreMovie.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        rvListGenreMovie.setAdapter(adapterGenreMovies);
                        adapterGenreMovies.notifyDataSetChanged();

                        JSONArray arrProdCompanies = objResult.getJSONArray("production_companies");
                        for (int j = 0; j < arrProdCompanies.length(); j++){
                            JSONObject objProdCompanies = arrProdCompanies.getJSONObject(j);

                            prodCompaniesList.add(j, new ProductionCompaniesModel("" + objProdCompanies.getString("id"), "" + objProdCompanies.getString("logo_path"), "" + objProdCompanies.getString("name"), "" + objProdCompanies.getString("origin_country")));
                        }

                        adapterProductionCompanies = new AdapterProductionCompanies(DetailMovies.this, prodCompaniesList);
                        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(DetailMovies.this, LinearLayoutManager.HORIZONTAL, false);
                        rvListProdCompanies.setLayoutManager(mLayoutManager);
                        rvListProdCompanies.setItemAnimator(new DefaultItemAnimator());
                        rvListProdCompanies.setItemViewCacheSize(prodCompaniesList.size());
                        rvListProdCompanies.setDrawingCacheEnabled(true);
                        rvListProdCompanies.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        rvListProdCompanies.setAdapter(adapterProductionCompanies);
                        adapterProductionCompanies.notifyDataSetChanged();


                        JSONArray arrProdCountries = objResult.getJSONArray("production_countries");
                        for (int k = 0; k < arrProdCountries.length(); k++){
                            JSONObject objProdCountries = arrProdCountries.getJSONObject(k);

                            prodCountriesList.add(k, new ProductionCountriesList("" + objProdCountries.getString("iso_3166_1"),"" + objProdCountries.getString("name")));
                        }

                        adapterProductionCountries = new AdapterProductionCountries(DetailMovies.this, prodCountriesList);
                        final RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(DetailMovies.this, LinearLayoutManager.HORIZONTAL, false);
                        rvListProdCountries.setLayoutManager(mLayoutManager2);
                        rvListProdCountries.setItemAnimator(new DefaultItemAnimator());
                        rvListProdCountries.setItemViewCacheSize(prodCountriesList.size());
                        rvListProdCountries.setDrawingCacheEnabled(true);
                        rvListProdCountries.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        rvListProdCountries.setAdapter(adapterProductionCountries);
                        adapterProductionCountries.notifyDataSetChanged();
                    } catch (JSONException e){
                        e.printStackTrace();
                        mApiConnect.closeDialogloading();
                    } catch (IOException e){
                        e.printStackTrace();
                        mApiConnect.closeDialogloading();
                    }

                    viewReviewMovies();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mApiConnect.closeDialogloading();
                Log.e("NAIKMOBIL", " = "+t.getMessage());
            }
        });
    }

    public void viewReviewMovies(){

        mApiConnect.showdialogloading();

        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", "92c884a51f746457056143b22b374aa0");

        reviewsList = new ArrayList<>();
        reviewsList.clear();

        mApiInterface = APIUrl.getAPIService();
        mApiInterface.getMovieReview(movieId,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{

                        mApiConnect.closeDialogloading();
                        String result = response.body().string();
                        JSONObject objResult = new JSONObject(result);

                        Log.i("OBJRESULTREVIEW", " === " + objResult);

                        JSONArray arrResult = objResult.getJSONArray("results");

                        if (arrResult.toString().equals("[]")){
                            tvReviewKosong.setVisibility(View.VISIBLE);
                        } else {
                            for (int m = 0; m < arrResult.length(); m++){
                                JSONObject objReviews = arrResult.getJSONObject(m);

                                reviewsList.add(m, new ReviewsModel("" + objReviews.getString("author"), "" + objReviews.getString("content"), "" + objReviews.getString("id"), "" + objReviews.getString("url")));
                            }

                            adapterReviewsModel = new AdapterReviewsModel(DetailMovies.this, reviewsList);
                            final RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(DetailMovies.this);
                            rvListReviewMovie.setLayoutManager(mLayoutManager4);
                            rvListReviewMovie.setItemAnimator(new DefaultItemAnimator());
                            rvListReviewMovie.setItemViewCacheSize(reviewsList.size());
                            rvListReviewMovie.setDrawingCacheEnabled(true);
                            rvListReviewMovie.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            rvListReviewMovie.setAdapter(adapterReviewsModel);
                            adapterReviewsModel.notifyDataSetChanged();

                            viewVideoTrailer();
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                        mApiConnect.closeDialogloading();
                    } catch (IOException e){
                        e.printStackTrace();
                        mApiConnect.closeDialogloading();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mApiConnect.closeDialogloading();
            }
        });
    }

    public void viewVideoTrailer(){

        mApiConnect.showdialogloading();

        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", "92c884a51f746457056143b22b374aa0");

        moviesTrailerModelList = new ArrayList<>();
        moviesTrailerModelList.clear();

        mApiInterface = APIUrl.getAPIService();
        mApiInterface.getMovieTrailer(movieId, params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{
                        mApiConnect.closeDialogloading();
                        String result = response.body().string();
                        JSONObject objResult = new JSONObject(result);

                        Log.i("RESULTVIDEO", " === " + objResult);

                        JSONArray arrVideo = objResult.getJSONArray("results");
                        if (arrVideo.toString().equals("[]")){
                            Toast.makeText(DetailMovies.this, "Movie Trailer Kosong", Toast.LENGTH_SHORT).show();
                        } else {
                            String[] type = new String[arrVideo.length()];

                            for (int i = 0; i < arrVideo.length(); i++) {
                                JSONObject objVideo = arrVideo.getJSONObject(i);

                                MoviesTrailerModel moviesTrailerModel = new MoviesTrailerModel(
                                        "" + objVideo.getString("id"), "" + objVideo.getString("iso_639_1"), "" + objVideo.getString("iso_3166_1"), objVideo.getString("key"),
                                        "" + objVideo.getString("name"), "" + objVideo.getString("site"), "" + objVideo.getString("size"), type[i] = objVideo.getString("type")
                                );

                                if (type[i].equals("Trailer")) {
                                    playVideo(objVideo.getString("key"));
                                    Log.i("URL", " === " + objVideo.getString("key"));
                                }

                            }
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                        mApiConnect.closeDialogloading();
                    } catch (IOException e){
                        e.printStackTrace();
                        mApiConnect.closeDialogloading();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mApiConnect.closeDialogloading();
            }
        });
    }

    public void playVideo(String key){
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String url = key;
                youTubePlayer.loadVideo(url,0);
                Log.i("URL", " === " + url);
            }
        });
    }
}
