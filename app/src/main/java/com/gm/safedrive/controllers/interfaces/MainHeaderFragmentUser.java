package com.gm.safedrive.controllers.interfaces;

import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gm.safedrive.R;
import com.gm.safedrive.application.Current;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.controllers.LoginActivity;
import com.gm.safedrive.models.User;

import java.util.Objects;

/* MainHeaderFragmentUser reprend le même principe que la classe MainHeaderActivityUser, mais avec un fragment à la place
    * d'une activité */
public class MainHeaderFragmentUser extends Fragment  implements PopupMenu.OnMenuItemClickListener {

    public String getFragmentName(){
        return "";
    }
    public int getProfilePhotoId(){
        return UserBank.getProfilePhotoId(new UserBank().getSessionUser((ContextWrapper) getContext()));
    }

    /* POPUP MENU :
    * Méthode pour afficher un menu popup lorsqu'on appuie sur un élément graphique (mBtnMoreMenu)
    */
    public void showMainHeaderMenu(View v){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_main_header);
        popupMenu.show();
        Log.i("FRAGMENT","showMainHeaderMenu: called.");
    }

    /*
        Lorsqu'on clique sur l'un des éléments du popup menu (Utilisation du switch pour savoir lequel)
    */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.i("FRAGMENT", "onMenuItemClick invoked.");
        switch (item.getItemId()){
            case R.id.menu_main_header_settings:
                Toast.makeText(getActivity(), "Item 1 clicked !",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_main_header_logout:
                // Déconnexion
                Toast.makeText(getActivity(), "Item 2 clicked !",Toast.LENGTH_SHORT).show();
                // Je supprime l'utilisateur session
                Current.deleteKeyValueFromSharedPreferences(Objects.requireNonNull(getActivity()), User.KEY);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                return true;
            default:
                return false;
        }
    }
}
