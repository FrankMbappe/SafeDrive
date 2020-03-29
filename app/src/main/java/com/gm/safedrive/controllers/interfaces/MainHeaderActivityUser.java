package com.gm.safedrive.controllers.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import com.gm.safedrive.banks.UserBank;

/*  La classe MainHeaderActivityUser est une sorte d'interface pour toutes les activités qui possèdent
 *   le fragment MainHeader. Elle extends déjà AppCompatActivity donc les activités qui l'implémentent n'ont
 *   pas besoin de l'implémenter à nouveau. Elle ne possède que les méthodes génériques que j'utilise dans le
 *   corps de la classe du Fragment */

public class MainHeaderActivityUser extends AppCompatActivity {

    public String getActivityName(){
        return "";
    }
    public int getProfilePhotoId(){
        return UserBank.getSessionProfilePhotoId();
    }
}
