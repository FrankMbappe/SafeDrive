package com.gm.safedrive.controllers.interfaces;

import android.content.ContextWrapper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.UserBank;

/*  La classe MainHeaderActivityUser est une sorte de semi-interface pour toutes les activités qui possèdent
 *   le fragment MainHeader. Elle extends déjà AppCompatActivity donc les activités qui l'implémentent n'ont
 *   pas besoin de l'implémenter à nouveau. Elle ne possède que les méthodes génériques que j'utilise dans le
 *   corps de la classe du Fragment */

public class MainHeaderActivityUser extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {

    public String getActivityName(){
        return "";
    }
    public int getProfilePhotoId(){
        return UserBank.getProfilePhotoId(new UserBank().getSessionUser((ContextWrapper) getBaseContext()));
    }

    /* POPUP MENU :
    Méthode pour afficher un menu popup lorsqu'on appuie sur un élément graphique (mBtnMoreMenu)
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
                Toast.makeText(this, "Item 1 clicked !",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_main_header_logout:
                Toast.makeText(this, "Item 2 clicked !",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    /* Concrètement, setSearchedValue est juste une méthode servant à effectuer une action sur une activité
    * à partir du texte passé en paramètre. Cette méthode est appelée par le fragment SearchBarFragment, qui dans
    * son addTextChangedListener l'exécute. Libre aux classes enfant de juger quoi faire de cette valeur.
    * Ex: Dans l'activité BrandActivity, je l'utilise pour créer un nouvel Adapter pour le RecyclerView. */
    public void setSearchedValue(String searchedValue){
        // Child classes will override this logic / Les classes enfants seront chargées d'établir la logique
    }

    /* Je reprends le même principe que le setSearchValue, cette fois-ci je passe juste un booléen  */
    public void hideViewsOnBaseFocus(boolean isFocused){
        // Child classes will override this logic / Les classes enfants seront chargées d'établir la logique
    }
}
