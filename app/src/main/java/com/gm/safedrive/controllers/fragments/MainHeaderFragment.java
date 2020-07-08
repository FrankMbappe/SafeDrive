package com.gm.safedrive.controllers.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.InflateException;
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
import com.gm.safedrive.controllers.interfaces.MainHeaderFragmentUser;

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

        /* MainHeaderActivityUser et MainHeaderFragmentUser sont des classes semi-interfaces que les activités ou les
         * fragments utilisant le MainHeaderFragment implémentent pour pouvoir l'utiliser. Je joue donc de ces semi-in
         * terfaces pour récupérer le nom du parent (à partir de la méthode getActivityName ou getFragmentName de celui-ci).
         */
        String parentName = "";
        try{
            parentName = ((MainHeaderActivityUser) getActivity()).getActivityName();
            Log.i("FRAGMENT","onCreateView: called. The parent is an activity and its name is " + parentName);
            mProfilePhoto.setImageResource(((MainHeaderActivityUser) Objects.requireNonNull(getActivity())).getProfilePhotoId());
        }catch (NullPointerException ex){
            parentName = "";
        }
        //TODO: Remarques - Ceci est laborieux. Capturer précisément l'exception du cast ci-dessus
        catch (Exception ex){
            if(getParentFragment() != null){
                parentName = ((MainHeaderFragmentUser) getParentFragment()).getFragmentName();
                mProfilePhoto.setImageResource(((MainHeaderFragmentUser) Objects.requireNonNull(getParentFragment())).getProfilePhotoId());
                Log.i("FRAGMENT","onCreateView: called. The parent is a fragment and its name is " + parentName);
            }else {
                parentName = "";
            }
        }
        mContextName.setText((parentName != null) ? parentName : "");

        return rootView;
    }

}
