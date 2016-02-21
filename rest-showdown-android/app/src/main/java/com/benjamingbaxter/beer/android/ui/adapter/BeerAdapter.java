package com.benjamingbaxter.beer.android.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.benjamingbaxter.beer.android.R;
import com.benjamingbaxter.beer.model.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerHolder> {

    List<Beer> beers;

    public BeerAdapter() {
        this.beers = new ArrayList<>();
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
        notifyDataSetChanged();
    }

    @Override
    public BeerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_row, parent, false);
        return new BeerHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerHolder holder, int position) {
        Beer beer = beers.get(position);

        holder.name.setText(beer.getName());
        holder.category.setText(beer.getCategory());
        holder.abv.setText(beer.getAbv() + "%");

        Picasso.with(holder.image.getContext())
                .load(beer.getImageUrl())
                .error(R.drawable.ic_local_cafe_green_light_32dp)
                .placeholder(R.drawable.ic_local_cafe_green_light_32dp)
                .resize(100, 100)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    static class BeerHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.category)
        TextView category;
        @Bind(R.id.abv)
        TextView abv;

        public BeerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
