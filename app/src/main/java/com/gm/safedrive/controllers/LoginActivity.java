package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

    private TextView mSignUpLink;
    private EditText mEmailInput;
    private EditText mPasswordInput;
    private Button mLoginBtn;
    private UserFireDbHelper mUserFireDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSignUpLink = findViewById(R.id.activity_login_signup_link);
        mEmailInput = findViewById(R.id.activity_login_email_input);
        mPasswordInput = findViewById(R.id.activity_login_password_input);
        mLoginBtn = findViewById(R.id.activity_login_btn);

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
                    new UserFireDbHelper().read(new UserFireDbHelper.OnlineDataStatus() {
                        @Override
                        public void DataIsLoaded(List<User> users, List<String> keys) {
                            Log.d(TAG, "Data is loaded");
                            User user = new UserBank().getUserByEmailAndPassword(mEmailInput.getText().toString(), mPasswordInput.getText().toString(), users);

                            if(user != null){
                                Current.saveObjectToSharedPreferences(LoginActivity.this, User.KEY, user);
                                startActivity(new Intent(LoginActivity.this, VehiclesActivity.class));
                                Toast.makeText(LoginActivity.this, getString(R.string.str_you_are_now_logged_in), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, getString(R.string.str_no_user_found), Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "No user found ! User value is null");
                            }
                        }

                        @Override
                        public void DataIsInserted() {}

                        @Override
                        public void DataIsUpdated() {}

                        @Override
                        public void DataIsDeleted() {}
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, getString(R.string.str_error_empty_box), Toast.LENGTH_SHORT).show();
                }
            }
        });
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

}
