package com.gm.safedrive.controllers.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainHeaderFragment extends Fragment {
    private CircleImageView mProfilePhoto;
    private TextView mContextName;
    private ImageButton mBtnMoreMenu;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_header, container, true);

        mProfilePhoto = rootView.findViewById(R.id.fragment_header_profile_photo);
        mContextName = rootView.findViewById(R.id.fragment_header_title);
        mBtnMoreMenu = rootView.findViewById(R.id.fragment_header_control_more);
        mContext = rootView.getContext();

        String activityName = "";
        try{
            activityName = ((MainHeaderActivityUser) getActivity()).getActivityName();
        }catch (NullPointerException ex){
            activityName = "";
        }
        mContextName.setText((activityName != null) ? activityName : "");
        mProfilePhoto.setImageResource(((MainHeaderActivityUser) Objects.requireNonNull(getActivity())).getProfilePhotoId());
        Log.i("FRAGMENT","onCreateView: called. The activity name is " + activityName);

        return rootView;
    }

}
