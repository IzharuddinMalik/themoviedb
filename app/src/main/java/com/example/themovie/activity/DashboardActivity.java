package com.example.themovie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.themovie.R;
import com.example.themovie.adapter.AdapterMoviesList;
import com.example.themovie.api.APIConnect;
import com.example.themovie.api.APIUrl;
import com.example.themovie.api.BaseApiInterface;
import com.example.themovie.model.GenreModel;
import com.example.themovie.model.MovieListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    Spinner spinListeGenre;
    BaseApiInterface mApiInterface;
    APIConnect mApiConnect;
    String idGenres;
    List<MovieListModel> listModels;
    AdapterMoviesList adapterMoviesList;
    RecyclerView rvListMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        spinListeGenre = findViewById(R.id.spinListGenre);
        rvListMovies = findViewById(R.id.rvListMovies);

        View toastsucces = getLayoutInflater().inflate(R.layout.toast_success, null);
        View toastinfo = getLayoutInflater().inflate(R.layout.toast_information, null);
        View toastfail = getLayoutInflater().inflate(R.layout.toast_failed, null);

        mApiConnect = new APIConnect(DashboardActivity.this, toastsucces, toastinfo, toastfail);
        getListGenre();
    }

    public void getListGenre(){

        mApiConnect.showdialogloading();

        HashMap<String, String> params = new HashMap<>();
        params.put("language", "ID");
        params.put("api_key", "92c884a51f746457056143b22b374aa0");

        mApiInterface = APIUrl.getAPIService();
        mApiInterface.getMovieGenre(params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{

                        mApiConnect.closeDialogloading();

                        String result = response.body().string();
                        JSONObject objResult = new JSONObject(result);
                        JSONArray arrList = objResult.getJSONArray("genres");
                        String[] namaGenre = new String[arrList.length()+1];
                        String[] idGenre = new String[arrList.length()+1];

                        namaGenre[0] = "Pilih Genre";
                        idGenre[0] = "0";
                        for (int i = 0; i < arrList.length(); i++){
                            JSONObject objGenre = arrList.getJSONObject(i);
                            GenreModel genreModel = new GenreModel(
                                    idGenre[i+1] = objGenre.getString("id"),
                                    namaGenre[i+1] = objGenre.getString("name")
                            );

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DashboardActivity.this, R.layout.inflater_list_genre, namaGenre);

                            spinListeGenre.setAdapter(adapter);
                            spinListeGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idGenres = idGenre[position];
                                    getMovies(idGenres);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
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

    public void getMovies(String idGenreMov){

        mApiConnect.showdialogloading();

        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", "92c884a51f746457056143b22b374aa0");
        params.put("language", "ID");
        params.put("with_genres", idGenreMov);

        listModels = new ArrayList<>();
        listModels.clear();

        mApiInterface = APIUrl.getAPIService();
        mApiInterface.getMovies(params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{
                        mApiConnect.closeDialogloading();
                        String result = response.body().string();
                        JSONObject objResult = new JSONObject(result);

                        Log.i("PAGE", " === " + objResult.getString("page"));

                        if (objResult.getString("results").equals("[]")){
                            mApiConnect.showtoastfailed("Silahkan Pilih Genre Movies");
                        } else {
                            JSONArray arrMovies = objResult.getJSONArray("results");
                            for (int j = 0; j < arrMovies.length(); j++){
                                JSONObject objMovies = arrMovies.getJSONObject(j);

                                listModels.add(j, new MovieListModel("" + objMovies.getString("popularity"), "" + objMovies.getString("id"), "" + objMovies.getString("video"), "" + objMovies.getString("vote_count"), "" + objMovies.getString("vote_average"),
                                        "" + objMovies.getString("title"), "" + objMovies.getString("release_date"), "" + objMovies.getString("original_language"), "" + objMovies.getString("original_title"), "" + objMovies.getString("genre_ids"), "" + objMovies.getString("backdrop_path"),
                                        "" + objMovies.getString("adult"),"" + objMovies.getString("overview"), "" + objMovies.getString("poster_path")));
                            }

                            adapterMoviesList = new AdapterMoviesList(DashboardActivity.this, listModels);
                            final RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(DashboardActivity.this);
                            rvListMovies.setLayoutManager(mLayoutManager3);
                            rvListMovies.setItemAnimator(new DefaultItemAnimator());
                            rvListMovies.setItemViewCacheSize(listModels.size());
                            rvListMovies.setDrawingCacheEnabled(true);
                            rvListMovies.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            rvListMovies.setAdapter(adapterMoviesList);
                            adapterMoviesList.notifyDataSetChanged();
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
                Log.e("COBASARI", " = "+idGenreMov);
            }
        });
    }
}
