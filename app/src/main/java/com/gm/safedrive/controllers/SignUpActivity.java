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
import com.gm.safedrive.banks.BrandBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.banks.VehicleTypeBank;
import com.gm.safedrive.firebase.UserFireDbHelper;
import com.gm.safedrive.models.User;
import com.gm.safedrive.models.Vehicle;
import com.gm.safedrive.models.VehicleModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";

    private TextView mLoginLink;
    private EditText mFirstNameInput;
    private EditText mLastNameInput;
    private EditText mPhoneNumberInput;
    private EditText mEmailInput;
    private EditText mPasswordInput;
    private EditText mConfPasswordInput;
    private Button mSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mLoginLink = findViewById(R.id.activity_signup_link_login);
        mFirstNameInput = findViewById(R.id.activity_signup_firstname_input);
        mLastNameInput = findViewById(R.id.activity_signup_lastname_input);
        mPhoneNumberInput = findViewById(R.id.activity_signup_phone_number_input);
        mEmailInput = findViewById(R.id.activity_signup_email_input);
        mPasswordInput = findViewById(R.id.activity_signup_password_input);
        mConfPasswordInput = findViewById(R.id.activity_signup_conf_password_input);
        mSignUpBtn = findViewById(R.id.activity_signup_btn);

        init();
    }

    private void init(){
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!remainEmptyBoxes(mFirstNameInput, mLastNameInput, mPhoneNumberInput, mEmailInput, mPasswordInput, mConfPasswordInput)){
                    final boolean passwordIsOk = mPasswordInput.getText().toString().equals(mConfPasswordInput.getText().toString());
                    if(passwordIsOk){
                        Log.d(TAG, "init() : Password is okay, Waiting for create user completing.");

                        saveUserToOnlineDb();
                    }
                    else{
                        Log.d(TAG, "init() : Error ! Password and Confirm Password aren't equal !");
                        Toast.makeText(SignUpActivity.this, getString(R.string.str_error_pwd_confirm_password), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Log.d(TAG, "init() : Error ! There's empty boxes remaining !");
                    Toast.makeText(SignUpActivity.this, getString(R.string.str_error_empty_box), Toast.LENGTH_SHORT).show();
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

    private void saveUserToOnlineDb(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(
                "",
                DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(Calendar.getInstance().getTime()),
                "LT258B",
                new VehicleModel(
                        "POCAYMAN",
                        new BrandBank().getAll().get(23),
                        "Cayman",
                        "Fast",
                        2006,
                        8,
                        new VehicleTypeBank().getAll().get(0),
                        null
                ),
                8,
                1000,
                null
        ));
        vehicles.add(new Vehicle(
                "",
                DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(Calendar.getInstance().getTime()),
                "VXH254",
                new VehicleModel(
                        "LaFerrari",
                        new BrandBank().getAll().get(6),
                        "LaFerrari",
                        "Fast",
                        2014,
                        9,
                        new VehicleTypeBank().getAll().get(0),
                        null
                ),
                10,
                5000,
                null
        ));
        final User signingUpUser = new User(
                DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(Calendar.getInstance().getTime()),
                mEmailInput.getText().toString(),
                mPasswordInput.getText().toString(),
                mFirstNameInput.getText().toString(),
                mLastNameInput.getText().toString(),
                Integer.parseInt(mPhoneNumberInput.getText().toString()),
                vehicles,
                0,
                "",
                ""
        );


        new UserFireDbHelper().add(signingUpUser, new UserFireDbHelper.OnlineDataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                Log.d(TAG, "Data is loaded");
                if(new UserBank().userEmailAlreadyExist(signingUpUser.getEmail(), users)){
                    Toast.makeText(SignUpActivity.this, "This email address already exists !", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void DataIsInserted() {
                Log.d(TAG, "Data is inserted");
                Log.d(TAG, "saveUserToOnlineDb()> UseFireDbHelper().add() : The user with the email " + signingUpUser.getEmail() + " was successfully created");
                Toast.makeText(SignUpActivity.this, "The user's account has been succesfully created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
            @Override
            public void DataIsUpdated() {}
            @Override
            public void DataIsDeleted() {}
        });
    }

}
