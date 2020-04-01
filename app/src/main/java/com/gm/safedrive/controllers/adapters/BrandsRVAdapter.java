package com.gm.safedrive.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.BrandModelsActivity;
import com.gm.safedrive.models.Brand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class BrandsRVAdapter extends RecyclerView.Adapter<BrandsRVAdapter.ViewHolder>{
    private static final String TAG = "BrandsRVAdapter";
    private ArrayList<Brand> mBrands = new ArrayList<>();
    private Context mContext;

    public BrandsRVAdapter(Context context, ArrayList<Brand> brands) {
        mBrands = brands;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand_default, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        holder.mBrandLogo.setImageResource(mBrands.get(position).getLogoId());
        holder.mBrandName.setText(mBrands.get(position).getName());
        final int i = position;
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mBrands.get(i).getName());
                Intent intent = new Intent(mContext, BrandModelsActivity.class);

                // Je convertis l'objet marque choisi en String grâce à Gson et je l'envoie à l'autre activité avec un putExtra
                Gson gson = new GsonBuilder().create();
                intent.putExtra(BrandModelsActivity.EXTRA_CURRENT_BRAND, gson.toJson(mBrands.get(i)));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mBrands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout mLayout;
        ImageView mBrandLogo;
        TextView mBrandName;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_brand_default_layout);
            mBrandLogo = itemView.findViewById(R.id.item_brand_default_logo);
            mBrandName = itemView.findViewById(R.id.item_brand_default_name);
        }
    }
}
