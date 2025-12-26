package com.education.ztu;
import java.io.RandomAccessFile;

public class RandomFileEditor {

    public static void editFile(String path) {
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {

            raf.seek(0);
            raf.writeBytes("NEW PRODUCT\n");

            raf.seek(raf.length());
            raf.writeBytes("\nANOTHER PRODUCT");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
