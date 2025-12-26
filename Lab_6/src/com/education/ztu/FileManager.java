package com.education.ztu;
import java.io.File;
import java.io.IOException;
public class FileManager {

    public static void manageFiles() throws IOException {
        File innerDir = new File("directory_for_files/inner_directory");
        innerDir.mkdir();

        System.out.println("Absolute path: " + innerDir.getAbsolutePath());
        System.out.println("Parent directory: " + innerDir.getParent());

        File file1 = new File(innerDir, "file1.txt");
        File file2 = new File(innerDir, "file2.txt");

        file1.createNewFile();
        file2.createNewFile();

        file1.delete();

        File renamedDir = new File("directory_for_files/renamed_inner_directory");
        innerDir.renameTo(renamedDir);

        File mainDir = new File("directory_for_files");
        for (File f : mainDir.listFiles()) {
            System.out.println(
                    f.getName() + " | " +
                            (f.isFile() ? "File" : "Directory") +
                            " | size=" + f.length()
            );
        }
    }
}
