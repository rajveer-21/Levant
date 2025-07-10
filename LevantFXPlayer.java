import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class LevantFXPlayer {
    private MediaPlayer mediaPlayer;
    private boolean isPaused = false;

    public LevantFXPlayer() {
        // Initializes JavaFX runtime
        new JFXPanel();
    }

    public void load(File file) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Platform.runLater(() -> {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            isPaused = false;
        });
    }

    public void play() {
        if (mediaPlayer == null) return;

        Platform.runLater(() -> {
            if (isPaused) {
                mediaPlayer.play();
                isPaused = false;
            } else {
                mediaPlayer.stop();
                mediaPlayer.play();
            }
        });
    }

    public void pause() {
        if (mediaPlayer == null) return;

        Platform.runLater(() -> {
            mediaPlayer.pause();
            isPaused = true;
        });
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void stop() {
        if (mediaPlayer != null) {
            Platform.runLater(() -> mediaPlayer.stop());
        }
    }
}
