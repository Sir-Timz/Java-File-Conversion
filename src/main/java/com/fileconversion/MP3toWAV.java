package com.fileconversion;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MP3toWAV {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    }

    public static AudioInputStream getAudioStream(String path) throws UnsupportedAudioFileException, IOException {
        File audioFile = new File(path);
        audioFile.
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        return audioStream;
    }

    public static AudioFormat getAudioFormat(AudioInputStream audioStream) {
        AudioFormat audioFormat = audioStream.getFormat();
        return audioFormat;
    }

    public static File toWAV(AudioInputStream audioStream, String fileName ) throws IOException {
        File WAVFile = new File("C:ConvertedFiles/Audio/" + fileName + ".wav");
        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, WAVFile);
        return WAVFile;
    }
}
