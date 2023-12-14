package com.example.zakatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements view.OnClickListener {

    Button btnZakatCalculator, btnAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnAboutUs = findViewById(R.id.btnAboutUs);
        Button btnZakatCalculator = findViewById(R.id.btnZakatCalculator);

        btnAboutUs.setOnClickListener((View.OnClickListener) this);
        btnAboutUs.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public void onClick(View v) {
        if (v == btnZakatCalculator) {
            Intent intent = new Intent (this, ZakatCalculator.class);

            startActivity(intent);
        }
    }
}