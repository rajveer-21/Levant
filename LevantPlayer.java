import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Platform;
import java.io.File;

class LevantPlayer
{
    private boolean isPaused = false;
    private MediaPlayer levantPlayer;
    LevantPlayer()
    {
        new JFXPanel();
    }
    
    public void load(File song, Runnable onReady)
    {
        if(levantPlayer != null)
        levantPlayer.stop();
        Platform.runLater(()->
        {
            Media media = new Media(song.toURI().toString());
            levantPlayer = new MediaPlayer(media);
            isPaused = false;
            levantPlayer.setOnReady(onReady);
        });
    }
    
    public void play()
    {
        if(levantPlayer == null) 
        return;
        Platform.runLater(()->
        {
            if(isPaused == true)
            levantPlayer.play();
            else
            {
                levantPlayer.stop();
                levantPlayer.play();
            }
        });
    }
    
    public void pause()
    {
        if(levantPlayer == null)
        return;
        Platform.runLater(()->
        {
            levantPlayer.pause();
            isPaused = true;
        });
    }
    
    public void stop()
    {
        if(levantPlayer != null)
        {
        Platform.runLater(()->
        {
            levantPlayer.stop();
        });
        }
    }
    
    public boolean isPaused()
    {
        return isPaused;
    }
    
    public Media getMedia()
    {
        if(levantPlayer == null)
        return null;
        return levantPlayer.getMedia();
    }
}