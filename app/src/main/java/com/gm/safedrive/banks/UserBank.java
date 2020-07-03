package com.gm.safedrive.banks;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.User;

import java.util.ArrayList;

public class UserBank {
    public static User SESSION = null;
//    public static User SESSION = new User(
//            1,
//            "2020/03/21 01:48:51",
//            "abc@mail.com",
//            "admin",
//            "Ronald",
//            "Reagan",
//            656895348,
//            0,
//            "2020/03/21 01:48:51",
//            null
//    );

    /* Un utilisateur ne peut choisir de photo de profil que parmi les photos qui lui sont proposées.
     * Les nôtres sont stockées dans le drawable.
     * A partir de la valeur du profilePhotoId de l'utilisateur en cours dans un switch,
     * on retourne la valeur de l'Id d'un drawable. */

    public static int getSessionProfilePhotoId(){
        if(SESSION != null){
            switch (SESSION.getProfilePhotoId()){
                case 1:
                    return R.drawable.brand_color_bmw;
                default:
                    return R.mipmap.ic_launcher_round;
            }
        }
        else return -1;
    }
}
