package com.example.lab1.helper;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.Collectors;

public final class FileHelper {

    private final Object lock = new Object();

    public FileHelper() {

    }

    public void writeToFileSynchronized(String key, Integer value) {
        synchronized(lock) {
            writeToFile(key, value);
        }
    }

    public void writeToFileDesynchronized(String key, Integer value) {

        writeToFile(key, value);
    }

    private void writeToFile(String key, Integer value) {
        try {

            BufferedWriter out = new BufferedWriter(
                    new FileWriter("repository.txt", true));

            for (int i = 0; i < value; i++) {
                out.write(key + " ");
            }
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            out.write(String.valueOf(timestamp));
            out.newLine();
            out.close();
        } catch (IOException e) {

            System.out.println("exception occurred" + e);
        }
    }

    public static String readFromFile() {
        String contentAsString = null;

        try {

            BufferedReader in = new BufferedReader(
                    new FileReader("repository.txt"));
            contentAsString = in.lines().sorted().filter(line -> !line.equals("")).collect(Collectors.joining("\n"));
            in.close();
        } catch (IOException e) {

            System.out.println("exception occurred" + e);
        }

        return contentAsString;
    }

}
