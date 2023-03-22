package com.songdetails;

import com.songdetails.SongImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic
{
    // to store current position
    Long currentFrame;
    public static Clip clip;
    // current status of clip
    String status;
    AudioInputStream audioInputStream;
    static String filePath;
    // constructor to initialize streams and clip
    public PlayMusic()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // Work as the user enters his choice
    private void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            default:
                break;
        }

    }
    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }
    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void streamAudio() {
        SongImpl song = new SongImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Song to Listen By Using Song Id");
        int isSong = scanner.nextInt();
        try{
            String songPath = song.getSongPath(isSong);
            try {
                filePath = songPath;
                PlayMusic playMusic = new PlayMusic();
                playMusic.play();
                Scanner sc = new Scanner(System.in);
                while (true) {
                    System.out.println("1. pause");
                    System.out.println("2. resume");
                    System.out.println("3. restart");
                    System.out.println("4. Exit");
                    int c = sc.nextInt();
                    playMusic.gotoChoice(c);
                    if (c == 4) {
                        clip.stop();
                        return;
                    }
                }
            }catch(FileNotFoundException e){
                System.out.println("Song ID Didn't Match Please Try Again");
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}

