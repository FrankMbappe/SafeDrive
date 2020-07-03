package com.gm.safedrive.controllers;

import androidx.annotation.NonNull;
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
import com.gm.safedrive.models.User;
import com.gm.safedrive.models.Vehicle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDbStore;
    //private DatabaseReference mDatabase;

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
        mAuth = FirebaseAuth.getInstance();
        mDbStore = FirebaseFirestore.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        init();
    }

    private void init(){
        final boolean passwordIsOk = mPasswordInput.getText().toString().equals(mConfPasswordInput.getText().toString());
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!remainEmptyBoxes(mFirstNameInput, mLastNameInput, mPhoneNumberInput, mEmailInput, mPasswordInput, mConfPasswordInput)){

                    mAuth.createUserWithEmailAndPassword(mEmailInput.getText().toString(), mPasswordInput.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, getString(R.string.str_task_error), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(passwordIsOk){
                                setSessionUser();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            }
                            else{
                                Toast.makeText(SignUpActivity.this, getString(R.string.str_task_error), Toast.LENGTH_SHORT).show();
                            }
                        }
                        }
                    });
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

    private void setSessionUser(){
        Calendar c = Calendar.getInstance();
        final String userID = (FirebaseAuth.getInstance().getCurrentUser() != null)
                            ?FirebaseAuth.getInstance().getCurrentUser().getUid()
                            :"";
        if(!userID.equals("")){
            UserBank.SESSION = new User(
                    userID,
                    DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime()),
                    mEmailInput.getText().toString(),
                    mPasswordInput.getText().toString(),
                    mFirstNameInput.getText().toString(),
                    mLastNameInput.getText().toString(),
                    Integer.parseInt(mPhoneNumberInput.getText().toString()),
                    new ArrayList<Vehicle>(),
                    0,
                    "",
                    ""
            );
            // TODO: Save user to Firestore
            DocumentReference docRef = mDbStore.collection("users").document(userID);
            docRef.set(UserBank.SESSION).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "onSuccess: User profile is successfully created for " + userID);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: Something went wrong ! " + e.getMessage());
                }
            });
        }
    }

}
