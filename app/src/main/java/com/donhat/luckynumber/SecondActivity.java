package com.donhat.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get view instances
        TextView luckyNumberTextView = findViewById(R.id.lucky_number_text_view);

        // Receiving data from the MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // Generate random number
        int randomNumber = generateRandomNumber();
        luckyNumberTextView.setText("" + randomNumber);

        // Handle share btn
        Button shareBtn = findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(v1 -> shareData(name, randomNumber));
    }

    private int generateRandomNumber() {
        Random random = new Random();
        int upperLimit = 1000;

        return random.nextInt(upperLimit);
    }

    private void shareData(String name, int randomNumber) {
        // Implicit intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        // Additional info
        intent.putExtra(Intent.EXTRA_SUBJECT, name + " got lucky today!");
        intent.putExtra(Intent.EXTRA_TEXT, "His lucky number is " + randomNumber);

        startActivity(Intent.createChooser(intent, "Choose a Platform"));
    }
}