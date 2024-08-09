package com.example.myquestiongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LastGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_last_graph);

        // Find the WebView by its ID
        WebView webView = findViewById(R.id.webView);

        // Load the URL into the WebView
        webView.loadUrl("http://10.0.2.2:9999/clicker/displaylast");

        // Apply padding to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the next button by its ID
        Button nextButton = findViewById(R.id.button);

        // Set click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user score from the TextView
                TextView userScoreTextView = findViewById(R.id.userScoreText);
                int userScore = Integer.parseInt(userScoreTextView.getText().toString().split(": ")[1]);

                // Start the SecondQuestion activity
                Intent intent = new Intent(LastGraph.this, LastPage.class);
                intent.putExtra("score", userScore);
                startActivity(intent);
            }
        });

        // Retrieve user score from intent extras
        int userScore = getIntent().getIntExtra("score", 0);

        // Find the user score TextView by its ID
        TextView userScoreTextView = findViewById(R.id.userScoreText);

        // Update user score TextView with the retrieved score
        userScoreTextView.setText("User Score: " + userScore);
    }
}