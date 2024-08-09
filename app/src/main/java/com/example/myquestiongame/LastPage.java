package com.example.myquestiongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LastPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        // Retrieve user score from intent extras
        int userScore = getIntent().getIntExtra("score", 0);

        // Find the user score TextView by its ID
        TextView userScoreTextView = findViewById(R.id.textViewUserScore);

        // Update user score TextView with the retrieved score
        userScoreTextView.setText("User Score: " + userScore);

        // Find Button to go back to main page
        Button backToMainButton = findViewById(R.id.backToMainButton);

        // Set click listener for the button
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main page
                Intent intent = new Intent(LastPage.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it from the main page
            }
        });
    }
}
