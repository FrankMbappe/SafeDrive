package com.gm.safedrive.banks;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.gm.safedrive.R;
import com.gm.safedrive.application.Current;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.controllers.LoginActivity;
import com.gm.safedrive.controllers.VehiclesActivity;
import com.gm.safedrive.firebase.UserFireDbHelper;
import com.gm.safedrive.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserBank {
    public static final String TAG = "UserBank";

    public User getSessionUser(ContextWrapper context){
        return (User) Current.getObjectFromSharedPreferences(context, User.KEY, User.class);
    }

    public void updateSessionUserFromOnlineDb(final ContextWrapper context){
        final User sessionUser = getSessionUser(context);
        new UserFireDbHelper().read(new UserFireDbHelper.OnlineDataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                Log.d(TAG, "Data is loaded, ready to read");
                User user = getUserById(sessionUser.getId(), users);
                if(user != null){
                    Current.saveObjectToSharedPreferences(context, User.KEY, user);
                }
                else{
                    Log.d(TAG, "No user found ! User value is null");
                }
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    public static int getProfilePhotoId(User user){
        if(user != null){
            switch (user.getProfilePhotoId()){
                case 1:
                    return R.drawable.brand_color_bmw;
                default:
                    return R.mipmap.ic_launcher_round;
            }
        }
        else return -1;
    }

    public static User getUserById(String id, List<User> users){
        for(User user : users){
            if(user.getId().equals(id)){ return user; }
        }
        return null;
    }
    public boolean userAccountIsOkay(User user, List<User> users){
        // Pourra être modifié si je veux ajouter de nouveaux paramètres qui déterminent qu'un uti-
        // lisateur est valide
        boolean isOkay = true;
        if(users != null && user != null){
            for(User u : users){
                if(user.getEmail().toLowerCase().equals(u.getEmail().toLowerCase())){
                    isOkay = false;
                }
            }
        }
        return isOkay;
    }
    public User getUserByEmailAndPassword(String email, String password, List<User> listOfUsers){
        if(listOfUsers != null && listOfUsers.size() > 0){
            for(User user : listOfUsers){
                if(user.getEmail().toLowerCase().equals(email.toLowerCase()) && user.getPassword().equals(password)){
                    return user;
                }
            }
            return null;
        }
        return null;
    }
    public boolean userEmailAlreadyExist(String email, List<User> listOfUsers){
        if(listOfUsers != null && listOfUsers.size() > 0){
            for(User u : listOfUsers){
                if(u.getEmail().toLowerCase().equals(email.toLowerCase())){
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
