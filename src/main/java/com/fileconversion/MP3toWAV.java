package com.fileconversion;


import org.apache.commons.io.FilenameUtils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MP3toWAV {
    public static String fileName;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String path =  inputReader.nextLine();
        File convertedFile = toWAV(getAudioStream(path), fileName);
    }

    public static AudioInputStream getAudioStream(String path) throws UnsupportedAudioFileException, IOException {
        File audioFile = new File(path);
        setFileName(audioFile);
        AudioInputStream encodedStream =  AudioSystem.getAudioInputStream(audioFile);
        AudioFormat encodedFormat = encodedStream.getFormat();
        AudioFormat decodedFormat = createDecodedFormat(encodedFormat);
        AudioInputStream decodedStream = AudioSystem.getAudioInputStream(decodedFormat, encodedStream);
        return decodedStream;
    }



    public static File toWAV(AudioInputStream audioStream, String fileName ) throws IOException {
        File WAVFile = new File("C:/ConvertedFiles/Audio/" + fileName + ".wav");
        if (!WAVFile.getParentFile().exists()) {
            WAVFile.mkdirs();
            WAVFile.createNewFile();
        }
        System.out.println(WAVFile.getAbsolutePath());
        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, WAVFile);
        return WAVFile;
    }

    public static void setFileName(File file) {
        fileName = FilenameUtils.getBaseName(file.getName());
    }

    public static AudioFormat createDecodedFormat(AudioFormat oldFormat) {
        AudioFormat newFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, oldFormat.getSampleRate(), 16, oldFormat.getChannels(), oldFormat.getChannels() * 2, oldFormat.getSampleRate(), false);
        return newFormat;
    }
}
