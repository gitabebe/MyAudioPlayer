package com.example.myaudioplayer; // Replace with your package name

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare a MediaPlayer object
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in activity_main.xml
        setContentView(R.layout.activity_main);

        // Initialize buttons by finding them by their IDs from the layout
        Button buttonAudio1 = findViewById(R.id.button_audio1);
        Button buttonAudio2 = findViewById(R.id.button_audio2);
        Button buttonAudio3 = findViewById(R.id.button_audio3);
        Button buttonStop = findViewById(R.id.button_stop);

        // Set OnClickListener for Audio 1 button
        buttonAudio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play audio1.mp3
                playAudio(R.raw.audio1);
                Toast.makeText(MainActivity.this, "Playing Audio 1", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Audio 2 button
        buttonAudio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play audio2.mp3
                playAudio(R.raw.audio2);
                Toast.makeText(MainActivity.this, "Playing Audio 2", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Audio 3 button
        buttonAudio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play audio3.mp3
                playAudio(R.raw.audio3);
                Toast.makeText(MainActivity.this, "Playing Audio 3", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Stop button
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop currently playing audio
                stopAudio();
                Toast.makeText(MainActivity.this, "Audio Stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Helper method to play an audio file.
     * Stops any currently playing audio and starts the new one.
     * @param audioResId The resource ID of the audio file (e.g., R.raw.audio1)
     */
    private void playAudio(int audioResId) {
        // Stop any existing MediaPlayer instance before starting a new one
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Release resources associated with the MediaPlayer
            mediaPlayer = null; // Set to null to indicate it's no longer active
        }

        // Create a new MediaPlayer instance with the given audio resource ID
        mediaPlayer = MediaPlayer.create(this, audioResId);

        // Set a listener to release the MediaPlayer when playback is complete
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Release the MediaPlayer resources once the audio finishes playing
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });

        // Start playing the audio
        mediaPlayer.start();
    }

    /**
     * Helper method to stop the currently playing audio and release resources.
     */
    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();      // Stop playback
            mediaPlayer.release();   // Release resources
            mediaPlayer = null;      // Clear the MediaPlayer instance
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Ensure MediaPlayer resources are released when the activity is destroyed
        // This prevents memory leaks and ensures proper resource management
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
