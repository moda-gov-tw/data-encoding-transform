package com.fullstack.backend.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class PasswordUtil {

    private static final int SALT_LENGTH = 16;

    // 生成鹽
    public static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    // 使用鹽和密碼生成哈希值
    public static String hashPassword(String password, byte[] salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // 驗證密碼
    public static boolean verifyPassword(String inputPassword, String storedHashedPassword, byte[] storedSalt) throws Exception {
        String hashedInputPassword = hashPassword(inputPassword, storedSalt);
        return hashedInputPassword.equals(storedHashedPassword);
    }

    public static void main(String[] args) throws Exception {
        // 註冊過程
        String password = "mySecurePassword";
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        System.out.println(hashedPassword);

        // 存儲哈希值和鹽到數據庫（這裡用 Map 模擬）
        Map<String, String> userDatabase = new HashMap<>();
        userDatabase.put("hashedPassword", hashedPassword);
        userDatabase.put("salt", Base64.getEncoder().encodeToString(salt));
        System.out.println(userDatabase.get("salt"));
        // 登錄過程
        String inputPassword = "mySecurePassword"; // 用戶輸入的密碼
        byte[] retrievedSalt = Base64.getDecoder().decode(userDatabase.get("salt")); // 從數據庫檢索鹽值
        String storedHashedPassword = userDatabase.get("hashedPassword"); // 從數據庫檢索哈希值

        // 驗證密碼
        boolean isValid = verifyPassword(inputPassword, storedHashedPassword, retrievedSalt);
        System.out.println("Password is valid: " + isValid);
    }
}
