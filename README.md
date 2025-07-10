# Levant
A music player made using Java's Swing, AWT and JavaFX packages.
Can play **MP3, WAV, AIFF, AAC** files

# Directions to use - 
1. **Load the player by launching the LevantGUI class, wait for it to compile (only on the first use).**
2. **Click on Load Song and navigate to your required file.**
3. **Click on the ▶️ button to play and ⏸️ button to pause the playing song.**

# LevantPlayer
The primary player which uses JavaFX's MediaPlayer() class to play various songs.
Has the ability to pause and resume already playing songs through functions such as play(), pause(), stop() and resume().
Uses the Platform thread to seamlessly play songs without any threading synchronization loss to pause and play at any given instance.

# LevantGUI
Creates a sleek modern looking GUI with all necessary buttons for playback of audio files.

**Note - Can only play audio files not video files as audio.**

Thanks for using my app! Best regards.
