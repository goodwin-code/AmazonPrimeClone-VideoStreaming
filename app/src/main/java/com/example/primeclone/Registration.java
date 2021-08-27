package com.example.primeclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    EditText mname, memail, mpassword, mphone;
    Button mregisterBtn;
    TextView mLoginbtn;
    FirebaseAuth fauth;
    CheckBox chkbBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        chkbBox = findViewById(R.id.checkBox);
        mname = findViewById(R.id.Name);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mphone = findViewById(R.id.phoneNum);
        mregisterBtn = findViewById(R.id.registeMe);
        mLoginbtn = findViewById(R.id.alreadyAcc);


        fauth = FirebaseAuth.getInstance();
        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }


        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = memail.getText().toString().trim();
                String Password = mpassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    memail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    mpassword.setError("Password is required");
                    return;
                }
                if (Password.length() < 6) {
                    mpassword.setError("Password must be greater than 6 character");
                    return;
                }
                fauth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(Registration.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });
        chkbBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    mpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


    }
}

