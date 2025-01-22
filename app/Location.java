package com.example.intent;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Location extends AppCompatActivity {

    private EditText etAwal, etTujuan;

    private Button btnJalur;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        etAwal = findViewById(R.id.etAwal);
        etTujuan = findViewById(R.id.etTujuan);
        btnJalur = findViewById(R.id.btnJalur);

        btnJalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String asal = etAwal.getText().toString();
                String tujuan = etTujuan.getText().toString();

                DisplayJalur(asal, tujuan);
            }
        });
    }

    private void DisplayJalur(String asal, String tujuan) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + asal + "/" + tujuan);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apss/details?id=com.google.android.apps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }
    }
}
