package com.example.myquestiongame;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondQuestion extends AppCompatActivity {

    private WebView webView;  // for displaying web contents
    private Button buttonA; // Button for option A
    private Button buttonB; // Button for option B
    private Button buttonC; // Button for option C
    private Button buttonD; // Button for option D

    private int userScore = 0; // User score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        webView = findViewById(R.id.webView);

        buttonA = findViewById(R.id.buttonOption1); // Assuming buttonOption1 is for option A
        buttonB = findViewById(R.id.buttonOption2); // Assuming buttonOption2 is for option B
        buttonC = findViewById(R.id.buttonOption3); // Assuming buttonOption3 is for option C
        buttonD = findViewById(R.id.buttonOption4); // Assuming buttonOption4 is for option D

        // Retrieve user score from intent extras
        userScore = getIntent().getIntExtra("score", 0);

        // Find the user score TextView by its ID
        TextView userScoreTextView = findViewById(R.id.textViewUserScore);

        // Update user score TextView with the retrieved score
        userScoreTextView.setText("User Score: " + userScore);

        // Set OnClickListener for button A
        buttonA.setOnClickListener(v -> {
            // Load URL for option A
            webView.loadUrl("http://10.0.2.2:9999/clicker/selecttwo?choice=A");
            checkAnswerAndUpdateScore("A");
        });

        // Set OnClickListener for button B
        buttonB.setOnClickListener(v -> {
            // Load URL for option B
            webView.loadUrl("http://10.0.2.2:9999/clicker/selecttwo?choice=B");
            checkAnswerAndUpdateScore("B");
        });

        // Set OnClickListener for button C
        buttonC.setOnClickListener(v -> {
            // Load URL for option C
            webView.loadUrl("http://10.0.2.2:9999/clicker/selecttwo?choice=C");
            checkAnswerAndUpdateScore("C");
        });

        // Set OnClickListener for button D
        buttonD.setOnClickListener(v -> {
            // Load URL for option D
            webView.loadUrl("http://10.0.2.2:9999/clicker/selecttwo?choice=D");
            checkAnswerAndUpdateScore("D");
        });
    }

    private void checkAnswerAndUpdateScore(String choice) {
        if (isCorrectAnswer(choice)) {
            userScore++; // Increment score if the answer is correct
        }
        startActivityWithScore(choice);
    }

    private boolean isCorrectAnswer(String choice) {
        // Implement your logic to check if the given choice is correct
        // For example, you can compare the choice with the correct answer
        // Replace this logic with your actual implementation
        return choice.equals("C");
    }

    private void startActivityWithScore(String choice) {
        Intent intent = new Intent(SecondQuestion.this, SecondGraph.class);
        intent.putExtra("score", userScore); // Pass the updated user score to the next activity
        intent.putExtra("choice", choice); // Pass the user's choice to the next activity
        startActivity(intent);
    }
}

