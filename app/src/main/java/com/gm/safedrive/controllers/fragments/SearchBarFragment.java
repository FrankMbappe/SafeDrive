package com.gm.safedrive.controllers.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;

public class SearchBarFragment extends Fragment {
    private EditText mSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_bar, container, true);

        mSearch = rootView.findViewById(R.id.fragment_search_bar_search);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("FRAGMENT","SearchBarFragment : mSearch.beforeTextChanged -> s = " + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("FRAGMENT","SearchBarFragment : mSearch.onTextChanged -> s = " + s.toString());
                ((MainHeaderActivityUser)getActivity()).setSearchedValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("FRAGMENT","SearchBarFragment : mSearch.afterTextChanged -> s = " + s.toString());
            }
        });
        mSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ((MainHeaderActivityUser)getActivity()).hideViewsOnBaseFocus(hasFocus);
                if(hasFocus){
                    Log.i("FRAGMENT","SearchBarFragment : EditText is focused.");
                }
                else {
                    Log.i("FRAGMENT","SearchBarFragment : EditText lost focus.");
                }
            }
        });

        return rootView;
    }
}
