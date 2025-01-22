package com.example.maulanaproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    private TextView tvResult;
    private String input = "";
    private String operator = "";
    private double firstNumber = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        ImageView imageView = findViewById(R.id.myImageView);

        mediaPlayer = MediaPlayer.create(this, R.raw.cihuyy);

        imageView.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop(); // Berhenti jika sedang diputar
                mediaPlayer.reset(); // Reset MediaPlayer
                mediaPlayer = MediaPlayer.create(this, R.raw.cihuyy); // Buat ulang MediaPlayer
            }
            mediaPlayer.setVolume(80f, 80f); // Set volume maksimum
            mediaPlayer.start(); // Mulai memutar suara
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String value = button.getText().toString();

                switch (value) {
                    case "C":
                        input = "";
                        operator = "";
                        firstNumber = 0;
                        tvResult.setText("0");
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        operator = value;
                        try {
                            firstNumber = Double.parseDouble(input);
                        } catch (NumberFormatException e) {
                            tvResult.setText("Error");
                            input = "";
                            return;
                        }
                        input = "";
                        break;
                    case "=":
                        double secondNumber;
                        try {
                            secondNumber = Double.parseDouble(input);
                        } catch (NumberFormatException e) {
                            tvResult.setText("Error");
                            return;
                        }
                        double result = 0;
                        switch (operator) {
                            case "+":
                                result = firstNumber + secondNumber;
                                break;
                            case "-":
                                result = firstNumber - secondNumber;
                                break;
                            case "*":
                                result = firstNumber * secondNumber;
                                break;
                            case "/":
                                if (secondNumber != 0) {
                                    result = firstNumber / secondNumber;
                                } else {
                                    tvResult.setText("Error");
                                    return;
                                }
                                break;
                        }
                        tvResult.setText(String.valueOf(result));
                        input = String.valueOf(result);
                        operator = "";
                        break;
                    default:
                        input += value;
                        tvResult.setText(input);
                }
            }
        };

        // Pasang listener ke semua tombol
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnEquals, R.id.btnClear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }
}