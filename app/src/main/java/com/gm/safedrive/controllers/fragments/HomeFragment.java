package com.gm.safedrive.controllers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.gm.safedrive.R;
import com.gm.safedrive.controllers.OperationActivity;
import com.gm.safedrive.controllers.interfaces.MainHeaderFragmentUser;

public class HomeFragment extends MainHeaderFragmentUser {
    private FloatingActionButton mActionFullRefueling;
    //private FloatingActionButton mActionPartRefueling;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Pour appliquer la méthode showMainHeaderMenu au bouton directement
        setShowMainHeaderMenu(rootView.findViewById(R.id.fragment_header_control_more));

        mActionFullRefueling = rootView.findViewById(R.id.fragment_home_action_1);
        //mActionPartRefueling = rootView.findViewById(R.id.fragment_home_action_2);

        mActionFullRefueling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rootView.getContext(), OperationActivity.class));
                Toast.makeText(rootView.getContext(), "Full refueling", Toast.LENGTH_SHORT).show();
            }
        });

        /*mActionPartRefueling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "Part refueling", Toast.LENGTH_SHORT).show();
            }
        });*/

        return rootView;
    }

    @Override
    public String getFragmentName(){
        return getString(R.string.str_home);
    }

    public void setShowMainHeaderMenu(View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainHeaderMenu(v.findViewById(R.id.fragment_header_control_more));
            }
        });
    }
}
