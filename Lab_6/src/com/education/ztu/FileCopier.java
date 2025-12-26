package com.education.ztu;

import java.io.*;

public class FileCopier {

    public static void copyTextFile(String src, String dest) {
        try (BufferedReader br = new BufferedReader(new FileReader(src));
             BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyImage(String src, String dest) {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int bytes;
            while ((bytes = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
