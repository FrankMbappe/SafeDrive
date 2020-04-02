package com.gm.safedrive.controllers.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.gm.safedrive.R;

public class VehicleValidationDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.str_notification_well_done)
                .setMessage(R.string.str_notification_save_vehicle_description)
                .setIcon(R.drawable.ic_check_circle_primary_36dp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si il n'y a rien ici, cela a pour effet de fermer la boîte de dialogue
                    }
                });

        return builder.create();
    }
}
