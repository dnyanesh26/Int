package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Encryption {

    public static String encrypt(String dataToEncrypt, SecretKeySpec key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecureRandom sr = new SecureRandom();
        byte[] iv = new byte[16];
        sr.nextBytes(iv);
        GCMParameterSpec gmcparspec = new GCMParameterSpec(128,iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gmcparspec);
        byte[] ciphertext =  cipher.doFinal(dataToEncrypt.getBytes());
        return base64Encode(iv) + ":" + base64Encode(ciphertext);

    }

    private static String base64Encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

}
