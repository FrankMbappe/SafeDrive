package com.gm.safedrive.controllers.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.banks.VehicleBank;
import com.gm.safedrive.controllers.MainActivity;
import com.gm.safedrive.data.DbManager;
import com.gm.safedrive.firebase.UserFireDbHelper;
import com.gm.safedrive.models.User;
import com.gm.safedrive.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehiclesRVAdapter extends RecyclerView.Adapter<VehiclesRVAdapter.ViewHolder>{
    private static final String TAG = "VehiclesRVAdapter";
    private ArrayList<Vehicle> mVehicles = new ArrayList<>();
    private Context mContext;

    public VehiclesRVAdapter(Context context, ArrayList<Vehicle> vehicles) {
        mVehicles = vehicles;
        mContext = context;
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
    }


    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: clicked on: "+ mVehicles.get(getAdapterPosition()).getModel().getName());
                    Toast.makeText(v.getContext(), mVehicles.get(getAdapterPosition()).getModel().getName(), Toast.LENGTH_SHORT).show();
                }
            });

            final PopupMenu.OnMenuItemClickListener listener = this;
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d(TAG, "onLongClick: clicked on: "+ mVehicles.get(getAdapterPosition()).getModel().getName());
                    Toast.makeText(v.getContext(), "Long click on "+ mVehicles.get(getAdapterPosition()).getModel().getName(), Toast.LENGTH_SHORT).show();

                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.inflate(R.menu.menu_item_vehicle_default);
                    popupMenu.setOnMenuItemClickListener(listener);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        popupMenu.setForceShowIcon(true);
                    }

                    popupMenu.show();

                    Log.i("FRAGMENT","showMainHeaderMenu: called.");

                    return true;
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Log.i("FRAGMENT", "onMenuItemClick invoked.");
            switch (item.getItemId()){
                case R.id.menu_item_vehicle_show:
                    showVehicleInfoDialog();
                    return true;
                case R.id.menu_item_vehicle_load:
                    //TODO: Chargement d'un véhicule pour la MainActivity
                    /* Véhicule chargé */
                    VehicleBank.LOADED_VEHICLE = mVehicles.get(getAdapterPosition());
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                    return true;
                case R.id.menu_item_vehicle_delete:
                    deleteRecyclerViewItem();
                    return true;
                default:
                    return false;
            }
        }

        public void showVehicleInfoDialog(){
            Dialog dialog = new Dialog(itemView.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_vehicle_info);

            TextView addDate = dialog.findViewById(R.id.dialog_vehicle_add_date);
            TextView userName = dialog.findViewById(R.id.dialog_vehicle_username);
            TextView brandName = dialog.findViewById(R.id.dialog_vehicle_brand_name);
            TextView modelName = dialog.findViewById(R.id.dialog_vehicle_model);
            TextView registrationNumber = dialog.findViewById(R.id.dialog_vehicle_registration_number);
            TextView defaultTankCap = dialog.findViewById(R.id.dialog_vehicle_default_tank_capacity);
            TextView currentTankCap = dialog.findViewById(R.id.dialog_vehicle_current_tank_capacity);
            TextView distanceCovered = dialog.findViewById(R.id.dialog_vehicle_distance_covered);

            addDate.setText(mVehicles.get(getAdapterPosition()).getCreatedDate());
            userName.setText(new UserBank().getSessionUser((ContextWrapper) itemView.getContext()).getFullName());
            brandName.setText(mVehicles.get(getAdapterPosition()).getModel().getBrand().getName());
            modelName.setText(mVehicles.get(getAdapterPosition()).getModel().getName());
            registrationNumber.setText(mVehicles.get(getAdapterPosition()).getRegistrationNumber());
            defaultTankCap.setText(mVehicles.get(getAdapterPosition()).getModel().getTankCapacity() + "L");
            currentTankCap.setText(mVehicles.get(getAdapterPosition()).getTankCapacity() + "L");
            distanceCovered.setText(mVehicles.get(getAdapterPosition()).getDistanceCovered() + "Km");


            dialog.show();
        }

        public void deleteRecyclerViewItem(){
            //TODO: Boîte de dialogue de confirmation de suppression

            // FIREBASE UPDATE HERE
            User sessionUser = new UserBank().getSessionUser((ContextWrapper) mContext);

            // CHECK DELETING HERE DUDE!!
            sessionUser.deleteVehicle(mVehicles.get(getAdapterPosition()).getId());
            new UserFireDbHelper().update(sessionUser.getId(), sessionUser, new UserFireDbHelper.OnlineDataStatus() {
                @Override
                public void DataIsLoaded(List<User> users, List<String> keys) {

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdated() {
                    mVehicles.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "1 item(s) has been removed.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "User vehicle list has been updated.");
                }

                @Override
                public void DataIsDeleted() {

                }
            });
            new UserBank().updateSessionUserFromOnlineDb((ContextWrapper) mContext);
        }
    }
}
