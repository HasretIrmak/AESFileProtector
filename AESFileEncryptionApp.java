import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

public class FileEncryptionApp {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static void generateKey(String keyFile) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256, new SecureRandom());
        SecretKey key = keyGen.generateKey();
        
        // Save the key to a file
        try (FileOutputStream fos = new FileOutputStream(keyFile)) {
            byte[] keyBytes = key.getEncoded();
            fos.write(keyBytes);
        }
    }

    public static void encryptFile(String inputFile, String outputFile, String keyFile) throws Exception {
        // Read the key
        byte[] keyBytes = new byte[32];
        try (FileInputStream fis = new FileInputStream(keyFile)) {
            fis.read(keyBytes);
        }
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            
            byte[] inputBytes = new byte[4096];
            int bytesRead;
            
            while ((bytesRead = fis.read(inputBytes)) != -1) {
                byte[] outputBytes = cipher.update(inputBytes, 0, bytesRead);
                if (outputBytes != null) {
                    fos.write(outputBytes);
                }
            }
            
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }

    public static void decryptFile(String inputFile, String outputFile, String keyFile) throws Exception {
        // Read the key
        byte[] keyBytes = new byte[32];
        try (FileInputStream fis = new FileInputStream(keyFile)) {
            fis.read(keyBytes);
        }
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            
            byte[] inputBytes = new byte[4096];
            int bytesRead;
            
            while ((bytesRead = fis.read(inputBytes)) != -1) {
                byte[] outputBytes = cipher.update(inputBytes, 0, bytesRead);
                if (outputBytes != null) {
                    fos.write(outputBytes);
                }
            }
            
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }

    public static void main(String[] args) {
        try {
            String keyFile = "secret.key";
            String inputFile = "test.txt";
            String encryptedFile = "encrypted.txt";
            String decryptedFile = "decrypted.txt";

            // Generate encryption key
            generateKey(keyFile);
            System.out.println("Key generated successfully!");

            // Encrypt the file
            encryptFile(inputFile, encryptedFile, keyFile);
            System.out.println("File encrypted successfully!");

            // Decrypt the file
            decryptFile(encryptedFile, decryptedFile, keyFile);
            System.out.println("File decrypted successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
