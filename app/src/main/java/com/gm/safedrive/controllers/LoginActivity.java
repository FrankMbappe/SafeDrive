package com.gm.safedrive.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gm.safedrive.R;
import com.gm.safedrive.application.Current;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.firebase.UserFireDbHelper;
import com.gm.safedrive.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

    private TextView mSignUpLink;
    private EditText mEmailInput;
    private EditText mPasswordInput;
    private Button mLoginBtn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private UserFireDbHelper mUserFireDbHelper;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSignUpLink = findViewById(R.id.activity_login_signup_link);
        mEmailInput = findViewById(R.id.activity_login_email_input);
        mPasswordInput = findViewById(R.id.activity_login_password_input);
        mLoginBtn = findViewById(R.id.activity_login_btn);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUserFireDbHelper = new UserFireDbHelper();

        Current.saveObjectToSharedPreferences(this, SplashScreenActivity.SAFEDRIVE_FIRST_TIME_LAUNCH, false);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mUser != null){
                    Toast.makeText(LoginActivity.this, getString(R.string.str_you_are_now_logged_in), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, VehiclesActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, getString(R.string.str_you_must_be_logged_in), Toast.LENGTH_SHORT).show();
                }
            }
        };

        init();
    }

    private void init(){
        mSignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!remainEmptyBoxes(mEmailInput, mPasswordInput)){
                    mFirebaseAuth.signInWithEmailAndPassword(mEmailInput.getText().toString(), mPasswordInput.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, getString(R.string.str_task_error), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                userID = mFirebaseAuth.getUid();
                                saveSessionUser();
                                startActivity(new Intent(LoginActivity.this, VehiclesActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, getString(R.string.str_error_empty_box), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    private boolean remainEmptyBoxes(EditText... boxes){
        boolean remainEmptyBoxes = false;
        for(EditText box : boxes){
            if(Current.stringIsNullOrWhitespace(box.getText().toString())){
                box.setError(getString(R.string.str_error_empty_box));
                box.requestFocus();
                remainEmptyBoxes = true;
            }
        }
        return remainEmptyBoxes;
    }

    private void saveSessionUser(){
        mUserFireDbHelper.read(new UserFireDbHelper.OnlineDataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                if(userID != null){
                    User user = UserBank.getUserById(userID, users);

                    if(user != null){
                        Current.saveObjectToSharedPreferences(LoginActivity.this, User.KEY, user);
                        Log.d(TAG, "The user found with the id " + userID + "has been successfully saved in shared preferences.");
                    }
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

}
