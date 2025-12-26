package com.education.ztu;
import java.io.*;
import java.util.zip.*;

public class ZipManager {

    public static void createZip(String zipPath, String sourceDir) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {

            File dir = new File(sourceDir);
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    FileInputStream fis = new FileInputStream(file);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    fis.close();
                    zos.closeEntry();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readZip(String zipPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("File in zip: " + entry.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
