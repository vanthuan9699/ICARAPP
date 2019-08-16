package com.example.icardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        Intent intent = getIntent();

        String extraName = intent.getStringExtra("name");
        String extraEmail = intent.getStringExtra("email");

        name.setText(extraName);
        email.setText(extraEmail);
    }
}
