package com.unasat.blackjack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class soundeffects {

    public void playSound(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                //clip.start();
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                //JOptionPane.showMessageDialog(null, "Press OKAY to play sound :P");
                clip.start();

                JOptionPane.showMessageDialog(null, "YOU LOSE!");
            } else {
                System.out.println("Can't find file");
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}

