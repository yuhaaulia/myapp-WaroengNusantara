package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class landing extends AppCompatActivity {
    TextView hargaLJava;

    String LJava = "hargaPesan";

    String hargaStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        hargaLJava = findViewById(R.id.hargaLanding);

        Bundle bundle = getIntent().getExtras();
        hargaStr = bundle.getString(LJava);
        hargaLJava.setText(hargaStr);
    }
}