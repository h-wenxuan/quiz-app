package com.example.myquestiongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.media.MediaPlayer; // Importing MediaPlayer

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mysound; // Declare MediaPlayer variable

    private WebView webView;  // for displaying web contents
    private TextView textViewErrorMessage;  // for displaying error message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mysound = MediaPlayer.create(MainActivity.this, R.raw.music); // Initialize MediaPlayer

        webView = findViewById(R.id.webView);
        textViewErrorMessage = findViewById(R.id.textViewErrorMessage);

        findViewById(R.id.btnGo).setOnClickListener(v -> {
            String userInput = ((EditText) findViewById(R.id.editTextName)).getText().toString();
            String url = "http://10.0.2.2:9999/clicker/storename?name=" + userInput;
            sendRequest(url);
        });
    }

    // Define playit method outside of onCreate
    public void playit(View v) {
        mysound.start();
    }

    private void sendRequest(String url) {
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    handleResponse(response.toString());
                } else {
                    // Handle unsuccessful response
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleResponse(String response) {
        runOnUiThread(() -> {
            if (response.equals("User already exists. Please choose another name.")) {
                // Display error message to the user and prompt for another name
                textViewErrorMessage.setVisibility(View.VISIBLE);
                textViewErrorMessage.setText(response);
            } else {
                // Navigate to FirstQuestion activity if the user does not already exist
                Intent intent = new Intent(MainActivity.this, FirstQuestion.class);
                startActivity(intent);
            }
        });
    }
}
