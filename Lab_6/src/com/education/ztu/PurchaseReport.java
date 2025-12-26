package com.education.ztu;
import java.io.*;

public class PurchaseReport {

    private static final String FILE_PATH = "directory_for_files/report.txt";

    public static void writeReport(String report) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH));
            writer.println(report);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readReport() {
        try {
            FileReader reader = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(reader);
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
