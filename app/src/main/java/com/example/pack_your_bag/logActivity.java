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

import com.example.pack_your_bag.MainActivity;

public class logActivity extends AppCompatActivity {

    EditText logUser, logPass;
    Button loginButton;
    TextView signRedirect;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        getSupportActionBar().hide();

        logUser = findViewById(R.id.logUsername);
        logPass = findViewById(R.id.logPassword);

        loginButton = findViewById(R.id.loginBtn2);
        signRedirect = findViewById(R.id.redirectSignup);

        db = new DBHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = logUser.getText().toString().trim();
                String pass = logPass.getText().toString().trim();

                // Check if username or password is empty
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(logActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed with login
                    Boolean result = db.checkUsernamePassword(user, pass);
                    if(result) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        Toast.makeText(logActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(logActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        signRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
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