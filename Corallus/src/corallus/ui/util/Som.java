/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.ui.util;

import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author itakenami
 */
public class Som {

    public static synchronized void play(String url) {


        try {
            //** add this into your application code as appropriate
            // Open an input stream  to the audio file.
            InputStream in = new FileInputStream(url);

            // Create an AudioStream object from the input stream.
            AudioStream as = new AudioStream(in);

            // Use the static class member "player" from class AudioPlayer to play
            // clip.
            AudioPlayer.player.start(as);

            // Similarly, to stop the audio.
            // AudioPlayer.player.stop(as);
        } catch (Exception e) {
            System.err.println("ERRO:" + e);
        }
    }
}
