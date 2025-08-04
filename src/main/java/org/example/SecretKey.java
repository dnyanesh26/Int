package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SecretKey {

    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        javax.crypto.SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String keyFromConfig = "sch111";
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey(keyFromConfig.toCharArray(), salt, iterationCount, keyLength);
        String pass = Encryption.encrypt("Ksh$12345", key);
        System.out.println(pass);
    }
}
