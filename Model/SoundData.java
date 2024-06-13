package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SoundData {

    //use to Clip Object
    private Clip bgmClip;

    private String backgroundFileName;

    public SoundData() {
        this.backgroundFileName= new String(FilePath);
        try {
            File bgmsoundFile = new File(backgroundFileName);
            AudioInputStream bgmaudioInputStream = AudioSystem.getAudioInputStream(bgmsoundFile);

            bgmClip = AudioSystem.getClip();

            bgmClip.open(bgmaudioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //start audio
    public void bgmPlay() {
        if (bgmClip != null) {
            bgmClip.start();
        }
    }


    //stop audio
    public void bgmStop() {
        if (bgmClip != null) {
            bgmClip.stop();
            bgmClip.setFramePosition(0);
        }
    }

        //"On, Off" Panel
        public JPanel bgmControll(){
        JPanel bgm = new JPanel();
        bgm.setLayout(new GridLayout(1,2));
        JButton bgmOn = new JButton("ON");
        JButton bgmOff = new JButton("Off");

        bgmOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgmPlay();
            }
        });

        bgmOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgmStop();
            }
        });

        bgm.add(bgmOn);
        bgm.add(bgmOff);

        return bgm;

    }
}
