package com.spotifylikeapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Sample audio files for testing
        String[] audioFiles = {
                "src/main/resources/audio/song1.mp3",
                "src/main/resources/audio/song2.mp3",
                "src/main/resources/audio/song3.mp3"
        };

        while (running) {
            System.out.println("\n[H]ome");
            System.out.println("[S]earch by title");
            System.out.println("[L]ibrary");
            System.out.println("[Q]uit");
            System.out.print("Enter an option: ");
            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "h":
                    System.out.println("Home - Displaying tracks");
                    displayTracks(audioFiles);
                    break;
                case "s":
                    System.out.print("Search by title: ");
                    String search = scanner.nextLine();
                    searchAndPlay(search, audioFiles);
                    break;
                case "l":
                    System.out.println("Library - Select a track number to play:");
                    playLibrary(audioFiles);
                    break;
                case "q":
                    System.out.println("Quitting the application.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    // Display tracks in Home
    public static void displayTracks(String[] audioFiles) {
        for (int i = 0; i < audioFiles.length; i++) {
            System.out.println((i + 1) + ". " + getTitleFromPath(audioFiles[i]));
        }
    }

    // Search and play track by title
    public static void searchAndPlay(String searchTitle, String[] audioFiles) {
        for (String filePath : audioFiles) {
            if (getTitleFromPath(filePath).toLowerCase().contains(searchTitle.toLowerCase())) {
                System.out.println("Playing: " + getTitleFromPath(filePath));
                AudioPlayer player = new AudioPlayer(filePath);
                player.play();
                return;
            }
        }
        System.out.println("Track not found.");
    }

    // Play track from Library
    public static void playLibrary(String[] audioFiles) {
        Scanner scanner = new Scanner(System.in);
        displayTracks(audioFiles);
        System.out.print("Enter track number: ");
        int trackNumber = scanner.nextInt();
        if (trackNumber >= 1 && trackNumber <= audioFiles.length) {
            String filePath = audioFiles[trackNumber - 1];
            System.out.println("Playing: " + getTitleFromPath(filePath));
            AudioPlayer player = new AudioPlayer(filePath);
            player.play();
        } else {
            System.out.println("Invalid track number.");
        }
    }

    // Helper method to extract the title from the file path
    public static String getTitleFromPath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
    }
}
