package com.example.pack_your_bag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pack_your_bag.MainActivity;
import com.example.pack_your_bag.logActivity;

public class startActivity extends AppCompatActivity {

    Button logOne, signOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button logOne = findViewById(R.id.startBtn1);
        Button signOne = findViewById(R.id.startBtn2);
        getSupportActionBar().hide();

        //open login activity
        logOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this, logActivity.class);
                startActivity(intent);
            }
        });

        //open signup activity

        signOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}