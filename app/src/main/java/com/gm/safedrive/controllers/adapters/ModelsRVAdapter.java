package com.gm.safedrive.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.BrandModelsActivity;
import com.gm.safedrive.controllers.FinalCVehicleActivity;
import com.gm.safedrive.models.VehicleModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ModelsRVAdapter extends RecyclerView.Adapter<ModelsRVAdapter.ViewHolder>{
    private static final String TAG = "ModelsRVAdapter";
    private ArrayList<VehicleModel> mModels = new ArrayList<>();
    private Context mContext;

    public ModelsRVAdapter(Context context, ArrayList<VehicleModel> models) {
        mModels = models;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand_model_default, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        Log.d(TAG,"onBindViewHolder: got " + mModels.get(position).getName());
        holder.mModelName.setText(mModels.get(position).getName());
        holder.mModelReleaseYear.setText(mModels.get(position).getReleaseYear() + "");

        final int i = position;
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mModels.get(i).getName());
                Intent intent = new Intent(mContext, FinalCVehicleActivity.class);

                // Je convertis l'objet marque choisi en String grâce à Gson et je l'envoie à l'autre activité avec un putExtra
                Gson gson = new GsonBuilder().create();
                intent.putExtra(FinalCVehicleActivity.EXTRA_CURRENT_MODEL, gson.toJson(mModels.get(i)));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout mLayout;
        TextView mModelName;
        TextView mModelReleaseYear;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_brand_model_layout);
            mModelName = itemView.findViewById(R.id.item_brand_model_name);
            mModelReleaseYear = itemView.findViewById(R.id.item_brand_model_release_year);
        }
    }
}
