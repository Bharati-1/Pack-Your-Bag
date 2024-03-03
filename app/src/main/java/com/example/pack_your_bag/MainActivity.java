package com.example.pack_your_bag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText username, password, confirmPass;
    Button signupBtn;
    TextView LoginRedirect;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        confirmPass = findViewById(R.id.editConfirmPass);

        signupBtn = findViewById(R.id.signupBtn);
        LoginRedirect = findViewById(R.id.loginRedirect);

        db = new DBHelper(this);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String cPass = confirmPass.getText().toString().trim();

                // Username validation
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(MainActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                if (user.length() < 6) {
                    Toast.makeText(MainActivity.this, "Username cannot be less than " + 6 + " characters", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                if (user.length() > 10) {
                    Toast.makeText(MainActivity.this, "Username cannot be more than" + 10 + " characters long", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
               if (!user.matches(".*[A-Z].*")) {
                    // Password doesn't contain an uppercase letter
                    Toast.makeText(MainActivity.this, "Username should contain at least one uppercase letter", Toast.LENGTH_SHORT).show();
                    return;
                }


                // Password validation
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(MainActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                if (pass.length() < 6 ) {
                    Toast.makeText(MainActivity.this, "Password cannot be less than " + 6 + " characters", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                if (pass.length() > 10) {
                    Toast.makeText(MainActivity.this, "Password cannot be more than" + 10 + " characters long", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                 if(!pass.matches(".*\\d.*")){
                    Toast.makeText(MainActivity.this, "Password should contain at least one digit", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass.matches(".*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?`~].*")) {
                    // Password doesn't contain a special character
                    Toast.makeText(MainActivity.this, "Password should contain at least one special character", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Confirm password validation
                if (TextUtils.isEmpty(cPass)) {
                    Toast.makeText(MainActivity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                if (!pass.equals(cPass)) {
                    Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }

                // If all validations pass, proceed with user registration
                Boolean userCheckResult = db.checkUsername(user);
                if (!userCheckResult) {
                    Boolean regResult = db.insertData(user, pass);
                    if (regResult) {
                        Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), logActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User already exists. Please sign in", Toast.LENGTH_SHORT).show();
                }
            }
        });


        LoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), logActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {
        // Call finish() to close the current activity
        super.onBackPressed();
        finishAffinity();


    }
}







