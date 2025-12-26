package com.education.ztu;

public class CredentialValidator {

    public static boolean checkCredentials(String login, String password, String confirmPassword) {
        try {
            if (!login.matches("[a-zA-Z0-9_]+") || login.length() >= 20) {
                throw new WrongLoginException("Login must contain only letters, digits, underscore and be less than 20 chars.");
            }

            if (!password.matches("[a-zA-Z0-9_]+") ||
                    password.length() >= 20 ||
                    !password.equals(confirmPassword)) {
                throw new WrongPasswordException("Password must contain only letters, digits, underscore, be less than 20 chars, and match confirmPassword.");
            }

            return true;

        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
