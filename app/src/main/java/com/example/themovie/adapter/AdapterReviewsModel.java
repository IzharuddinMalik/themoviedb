package com.example.themovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.model.ReviewsModel;
import com.example.themovie.utils.StylingUtils;

import java.util.List;

public class AdapterReviewsModel extends RecyclerView.Adapter<AdapterReviewsModel.ReviewViewHolder> {

    private Context mCtx;
    List<ReviewsModel> reviewsList;
    StylingUtils stylingUtils;

    public AdapterReviewsModel(Context context, List<ReviewsModel> reviewsList){
        this.mCtx = context;
        this.reviewsList = reviewsList;

        stylingUtils = new StylingUtils();
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflater_reviews_movie, parent, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public int getItemCount(){ return reviewsList.size(); }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        TextView tvAuthor, tvReviewComments;

        public ReviewViewHolder(View view){
            super(view);

            tvAuthor = view.findViewById(R.id.tvAuthorReviews);
            tvReviewComments = view.findViewById(R.id.tvReviewComments);

            stylingUtils.robotoRegularTextview(mCtx, tvAuthor);
            stylingUtils.robotoRegularTextview(mCtx, tvReviewComments);
        }
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position){
        ReviewsModel reviewsModel = reviewsList.get(position);

        holder.tvAuthor.setText(reviewsModel.getAuthorReviews());
        holder.tvReviewComments.setText(reviewsModel.getContentReviews());
    }
}
