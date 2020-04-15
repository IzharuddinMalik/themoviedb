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
import com.example.themovie.model.ProductionCountriesList;
import com.example.themovie.utils.StylingUtils;

import java.util.List;

public class AdapterProductionCountries extends RecyclerView.Adapter<AdapterProductionCountries.ProdCountriesViewHolder> {

    private Context mCtx;
    List<ProductionCountriesList> prodCountriesList;
    StylingUtils stylingUtils;

    public AdapterProductionCountries(Context context, List<ProductionCountriesList> prodCountriesList){
        this.mCtx = context;
        this.prodCountriesList = prodCountriesList;
        stylingUtils = new StylingUtils();
    }

    @Override
    public ProdCountriesViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflater_production_contries, parent, false);

        return new ProdCountriesViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return prodCountriesList.size();
    }

    public class ProdCountriesViewHolder extends RecyclerView.ViewHolder{
        TextView tvCountriesName;

        public ProdCountriesViewHolder(View view){
            super(view);

            tvCountriesName = view.findViewById(R.id.tvProductionContriesMovieDetail);

            stylingUtils.robotoRegularTextview(mCtx, tvCountriesName);
        }
    }

    @Override
    public void onBindViewHolder(ProdCountriesViewHolder holder, int position){

        ProductionCountriesList prodCountriesModel = prodCountriesList.get(position);

        holder.tvCountriesName.setText(prodCountriesModel.getNameCountry());
    }
}
