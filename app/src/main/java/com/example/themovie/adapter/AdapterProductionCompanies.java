package com.example.themovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.model.ProductionCompaniesModel;
import com.example.themovie.utils.StylingUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProductionCompanies extends RecyclerView.Adapter<AdapterProductionCompanies.ProductionViewHolder> {

    private Context mCtx;
    List<ProductionCompaniesModel> listProdCompanies;
    StylingUtils stylingUtils;

    public AdapterProductionCompanies(Context context, List<ProductionCompaniesModel> listProdCompanies){
        this.mCtx = context;
        this.listProdCompanies = listProdCompanies;
        stylingUtils = new StylingUtils();
    }

    @Override
    public ProductionViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflater_production_companies, parent, false);

        return new ProductionViewHolder(view);
    }

    @Override
    public int getItemCount(){ return listProdCompanies.size(); }

    public class ProductionViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPoster;
        TextView tvCompaniesName, tvCompaniesCountry;

        public ProductionViewHolder(View view){
            super(view);

            ivPoster = view.findViewById(R.id.ivPosterCompanies);
            tvCompaniesName = view.findViewById(R.id.tvCompaniesName);
            tvCompaniesCountry = view.findViewById(R.id.tvCompaniesCountry);

            stylingUtils.robotoMediumTextview(mCtx, tvCompaniesName);
            stylingUtils.robotoRegularTextview(mCtx, tvCompaniesCountry);
        }
    }

    @Override
    public void onBindViewHolder(ProductionViewHolder holder, int position){
        ProductionCompaniesModel productionCompaniesModel = listProdCompanies.get(position);

        holder.tvCompaniesName.setText(productionCompaniesModel.getNameCompanies());
        holder.tvCompaniesCountry.setText(productionCompaniesModel.getOriginCountry());
        Picasso.with(mCtx).load("https://api.themoviedb.org/3/movie" + productionCompaniesModel.getLogoPath()).into(holder.ivPoster);
    }
}
