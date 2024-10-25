package project_bankApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Hashes the password using SHA-256
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = messageDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Verifies if a plain text password matches the hashed password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) throws NoSuchAlgorithmException {
        return hashPassword(plainPassword).equals(hashedPassword);
    }
}

