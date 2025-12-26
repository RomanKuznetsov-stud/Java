package com.education.ztu;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Task2: Credential Check ===");
        boolean valid = CredentialValidator.checkCredentials("user_1", "pass_123", "pass_123");
        System.out.println("Credentials valid: " + valid);

        System.out.println("\n=== Task3: Purchase Report ===");
        PurchaseReport.writeReport("Apple | price=10 | qty=2\nBanana | price=5 | qty=3");
        PurchaseReport.readReport();

        System.out.println("\n=== Task6: File Management ===");
        FileManager.manageFiles();

        System.out.println("\n=== Task7: Zip Files ===");
        ZipManager.createZip("directory_for_files/archive.zip", "directory_for_files");
        ZipManager.readZip("directory_for_files/archive.zip");
    }
}
