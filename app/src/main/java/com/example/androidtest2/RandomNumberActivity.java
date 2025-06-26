package com.example.androidtest2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class RandomNumberActivity extends AppCompatActivity {
    private TextView tvRandomNumberResult;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);
        tvRandomNumberResult = findViewById(R.id.tvRandomNumberResult);
        Button btnGenerate = findViewById(R.id.btnGenerateRandomNumber);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = random.nextInt(10000) + 1;
                tvRandomNumberResult.setText(getString(R.string.random_number_result) + number);
            }
        });
    }
} 