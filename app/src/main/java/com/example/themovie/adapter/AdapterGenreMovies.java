package com.example.themovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.model.GenreModel;
import com.example.themovie.utils.StylingUtils;

import java.util.List;

public class AdapterGenreMovies extends RecyclerView.Adapter<AdapterGenreMovies.GenreMovieViewHolder> {

    private Context mCtx;
    private List<GenreModel> listGenre;
    StylingUtils stylingUtils;

    public AdapterGenreMovies(Context context, List<GenreModel> listGenre){
        this.mCtx = context;
        this.listGenre = listGenre;
        stylingUtils = new StylingUtils();
    }

    @Override
    public GenreMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflater_genre_movie_detail, parent, false);

        return new GenreMovieViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return listGenre.size();
    }

    public class GenreMovieViewHolder extends RecyclerView.ViewHolder{
        TextView tvGenre;

        public GenreMovieViewHolder(View view){
            super(view);

            tvGenre = view.findViewById(R.id.tvGenreMoviesDetail);

            stylingUtils.robotoMediumTextview(mCtx, tvGenre);
        }
    }

    @Override
    public void onBindViewHolder(GenreMovieViewHolder holder, int position){

        GenreModel genreModel = listGenre.get(position);

        holder.tvGenre.setText(genreModel.getNameGenre());
    }
}
