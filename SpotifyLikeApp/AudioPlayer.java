package com.spotifylikeapp;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayer {
    private Player player;
    private String filePath;

    public AudioPlayer(String filePath) {
        this.filePath = filePath;
    }

    // Method to play the MP3 file
    public void play() {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            // Initialize the player with the input stream
            player = new Player(fileInputStream);
            System.out.println("Playing: " + filePath);

            // Start playback (this blocks until the file is fully played)
            player.play();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (JavaLayerException e) {
            System.out.println("Error playing the file.");
            e.printStackTrace();
        }
    }

    // Method to stop the MP3 file (optional for more advanced features)
    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
