package com.gm.safedrive.controllers.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.safedrive.R;
import com.gm.safedrive.models.Vehicle;

import java.util.ArrayList;

public class VehiclesRVAdapter extends RecyclerView.Adapter<VehiclesRVAdapter.ViewHolder>{
    private static final String TAG = "VehiclesRVAdapter";
    private ArrayList<Vehicle> mVehicles = new ArrayList<>();
    private Context mContext;
    private Button mToggleButton;

    public VehiclesRVAdapter(Context context, ArrayList<Vehicle> vehicles, Button toggleButton) {
        mVehicles = vehicles;
        mContext = context;
        mToggleButton = toggleButton;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_default, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        holder.mBrandLogo.setImageResource(mVehicles.get(position).getModel().getBrand().getLogoId());
        holder.mTypeImage.setImageResource(mVehicles.get(position).getModel().getVehicleType().getLogoId());
        holder.mBrandName.setText(mVehicles.get(position).getModel().getBrand().getName());
        holder.mModelName.setText(mVehicles.get(position).getModel().getName());
        holder.mRegistrationNumber.setText(mVehicles.get(position).getRegistrationNumber());
        final int i = position;
        boolean toggle = false;
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mVehicles.get(i).getModel().getName());

                Toast.makeText(mContext, mVehicles.get(i).getModel().getName(), Toast.LENGTH_SHORT).show();

                mToggleButton.setVisibility(View.VISIBLE);
                mToggleButton.setEnabled(true);
                holder.mLayout.setBackgroundResource(R.color.colorBitDarker);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout mLayout;
        ImageView mBrandLogo;
        ImageView mTypeImage;
        TextView mBrandName;
        TextView mModelName;
        TextView mRegistrationNumber;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_vehicle_default_layout);
            mBrandLogo = itemView.findViewById(R.id.item_vehicle_default_brand_logo);
            mBrandName = itemView.findViewById(R.id.item_vehicle_brand);
            mModelName = itemView.findViewById(R.id.item_vehicle_model);
            mRegistrationNumber = itemView.findViewById(R.id.item_vehicle_matricule);
            mTypeImage = itemView.findViewById(R.id.item_vehicle_type_image);
        }
    }
}
