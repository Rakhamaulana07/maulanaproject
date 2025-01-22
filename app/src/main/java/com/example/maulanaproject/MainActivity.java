package com.example.maulanaproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer; // Deklarasi mediaPlayer
    private ImageView logo; // Deklarasi logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnmove = findViewById(R.id.btnmove);
        Button btnshare = findViewById(R.id.btnshare);
        Button btncall = findViewById(R.id.btncall);
        logo = findViewById(R.id.Logo); // Inisialisasi logo

        mediaPlayer = MediaPlayer.create(this, R.raw.yippie);

        logo.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop(); // Berhenti jika sedang diputar
                mediaPlayer.reset(); // Reset MediaPlayer
                mediaPlayer = MediaPlayer.create(this, R.raw.yippie); // Buat ulang MediaPlayer
            }
            mediaPlayer.start(); // Mulai memutar suara
        });

        btncall.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity3.class)));

        btnmove.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity2.class)));

        btnshare.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Bagikan le");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "bagi ke :"));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Lepaskan sumber daya MediaPlayer
            mediaPlayer = null; // Set ke null untuk menghindari kebocoran memori
        }
    }
}