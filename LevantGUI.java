import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.scene.media.Media;
import javafx.application.Platform;

public class levantGUI extends JFrame
{
    private LevantPlayer levantPlayer;
    private File currentSongFile;

    public levantGUI()
    {
        levantPlayer = new LevantPlayer();
        setTitle("Levant");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addGUIComponents();
    }

    public void addGUIComponents()
    {
        JToolBar toolbar = new JToolBar();
        toolbar.setBounds(0, 0, getWidth(), 20);
        toolbar.setFloatable(false);
        add(toolbar);

        JMenuBar menubar = new JMenuBar();
        toolbar.add(menubar);

        JMenu loadSongs = new JMenu("Load Songs");
        menubar.add(loadSongs);

        JLabel songArtist = new JLabel("Song Artist");
        songArtist.setBounds(0, 300, getWidth() - 20, 30);
        songArtist.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel songName = new JLabel("Song Name");
        songName.setBounds(0, 335, getWidth() - 20, 30);
        songName.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
        songName.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel albumArt = new JLabel();
        albumArt.setBounds(0, 50, getWidth() - 20, 240);
        albumArt.setHorizontalAlignment(SwingConstants.CENTER);

        add(albumArt);
        add(songName);
        add(songArtist);

        JMenuItem loadSong = new JMenuItem("Load Song");
        loadSong.addActionListener(e ->
        {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            currentSongFile = chooser.getSelectedFile();
            if(currentSongFile != null)
            {
                levantPlayer.load(currentSongFile, ()->
                {
                    Media media = levantPlayer.getMedia();
                    Platform.runLater(()->
                    {
                        Map<String, Object> metadata = media.getMetadata();
                        String title = (String)metadata.get("title");
                        String artist = (String)metadata.get("artist");
                        Object image = metadata.get("image");
                        if(title != null)
                        songName.setText(title);
                        else
                        songName.setText("Unknown");
                        if(artist != null)
                        songArtist.setText(artist);
                        else
                        songArtist.setText("Unknown");
                        if(image instanceof javafx.scene.image.Image)
                        {
                            javafx.scene.image.Image fxImage = (javafx.scene.image.Image)image;
                            int width = (int)fxImage.getWidth();
                            int height = (int)fxImage.getHeight();
                            BufferedImage bImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                            for(int y = 0; y < height; y++)
                            {
                                for(int x = 0; x < width; x++)
                                {
                                    int argb = fxImage.getPixelReader().getArgb(x, y);
                                    bImg.setRGB(x, y, argb);
                                }
                            }
                            Image scaled = bImg.getScaledInstance(albumArt.getWidth(), albumArt.getHeight(), java.awt.Image.SCALE_SMOOTH);
                            albumArt.setIcon(new ImageIcon(scaled));
                        }
                    });
                });
            }
        });
        loadSongs.add(loadSong);

        JPanel ButtonSystem = new JPanel();
        ButtonSystem.setBounds(0, 375, getWidth() - 20, 60);

        JButton play = new JButton("▶");
        JButton pause = new JButton("⏸");

        play.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                levantPlayer.play();
            }
        });

        pause.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent actionEvent)
           {
               levantPlayer.pause();
           }
        });

        play.setBorderPainted(false);
        pause.setBorderPainted(false);
        ButtonSystem.add(play);
        ButtonSystem.add(pause);
        ButtonSystem.setForeground(null);
        ButtonSystem.setBackground(null);
        add(ButtonSystem);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            new levantGUI().setVisible(true);
        });
    }
}
