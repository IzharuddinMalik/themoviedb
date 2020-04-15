package com.example.themovie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.model.MovieListModel;
import com.example.themovie.model.MoviesTrailerModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.net.ConnectException;
import java.util.List;

public class AdapterMovieTrailer extends RecyclerView.Adapter<AdapterMovieTrailer.MovieTrailerViewHolder> {

    private Context mCtx;
    List<MoviesTrailerModel> moviesTrailerList;

    public AdapterMovieTrailer(Context context, List<MoviesTrailerModel> moviesTrailerList){
        this.mCtx = context;
        this.moviesTrailerList = moviesTrailerList;
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflater_list_movie_trailer, parent, false);

        return new MovieTrailerViewHolder(view);
    }

    @Override
    public int getItemCount(){ return moviesTrailerList.size(); }

    public class MovieTrailerViewHolder extends RecyclerView.ViewHolder{
        YouTubePlayerView youTubePlayer;

        public MovieTrailerViewHolder(View view){
            super(view);

            youTubePlayer = view.findViewById(R.id.videoTrailer);
        }
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position){
        MoviesTrailerModel moviesTrailerModel = moviesTrailerList.get(position);

        if (moviesTrailerModel.getTypeMoviesTrailer().equals("Trailer")){
            Log.i("MOVIETYPE", " === " + moviesTrailerModel.getTypeMoviesTrailer().equals("Trailer"));
            holder.youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    String url = moviesTrailerModel.getKeyMoviesTrailer();
                    youTubePlayer.loadVideo(url,0);
                    Log.i("URL", " === " + url);
                }
            });
        } else {
            holder.youTubePlayer.setVisibility(View.GONE);
        }
    }
}
