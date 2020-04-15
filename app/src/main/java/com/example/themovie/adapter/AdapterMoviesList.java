package com.example.themovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.activity.DetailMovies;
import com.example.themovie.model.MovieListModel;
import com.example.themovie.utils.StylingUtils;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdapterMoviesList extends RecyclerView.Adapter<AdapterMoviesList.MoviesViewHolder> {

    Context mCtx;
    List<MovieListModel> listModels;
    StylingUtils stylingUtils;

    public AdapterMoviesList(Context context, List<MovieListModel> listModels){
        this.mCtx = context;
        this.listModels = listModels;
        stylingUtils = new StylingUtils();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflater_movie, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public int getItemCount(){ return listModels.size(); }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitleMovies, tvOriginalTitleMovies, tvPopularity, tvReleaseDate, tvVote, tvVoteAvg;
        ImageView ivPosterMovies;

        public MoviesViewHolder(View view){
            super(view);

            tvTitleMovies = view.findViewById(R.id.tvTitleMovies);
            tvOriginalTitleMovies = view.findViewById(R.id.tvOriginalTitleMovies);
            tvPopularity = view.findViewById(R.id.tvPopularityMovies);
            tvReleaseDate = view.findViewById(R.id.tvReleaseDateMovies);
            tvVote = view.findViewById(R.id.tvVoteMovies);
            tvVoteAvg = view.findViewById(R.id.tvVoteAvgMovies);
            ivPosterMovies = view.findViewById(R.id.ivPosterMovie);

            stylingUtils.robotoBoldTextview(mCtx, tvTitleMovies);
            stylingUtils.robotoRegularTextview(mCtx, tvOriginalTitleMovies);
            stylingUtils.robotoRegularTextview(mCtx, tvPopularity);
            stylingUtils.robotoRegularTextview(mCtx, tvReleaseDate);
            stylingUtils.robotoRegularTextview(mCtx, tvVote);
            stylingUtils.robotoRegularTextview(mCtx, tvVoteAvg);

            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(mCtx, DetailMovies.class);
                    intent.putExtra("movieId", listModels.get(position).getIdMoviesList());
                    mCtx.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position){

        MovieListModel movieListModel = listModels.get(position);

        holder.tvTitleMovies.setText(movieListModel.getTitleMoviesList());
        holder.tvOriginalTitleMovies.setText(movieListModel.getOriginalTitle());
        holder.tvPopularity.setText(movieListModel.getPopularityMovies());
        holder.tvReleaseDate.setText(movieListModel.getReleaseDateMoviesList());
        holder.tvVote.setText(movieListModel.getJumlahVote());
        holder.tvVoteAvg.setText(movieListModel.getVoteAverage());

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder().addHeader("api_key", "92c884a51f746457056143b22b374aa0")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Picasso picasso = new Picasso.Builder(mCtx)
                .downloader(new OkHttp3Downloader(client))
                .build();
        picasso.load("https://api.themoviedb.org/3/discover/movie" + movieListModel.getPosterPath()).into(holder.ivPosterMovies);

        Log.i("IMAGEPATH", " === " + movieListModel.getPosterPath());
    }
}
