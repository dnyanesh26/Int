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
import java.util.Base64;

public class Decryption {

    public static String decrypt(String string, SecretKeySpec key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gmcparspec = new GCMParameterSpec(128, base64decode(iv));
        pbeCipher.init(Cipher.DECRYPT_MODE, key, gmcparspec);
        return  new String(pbeCipher.doFinal(base64decode(property)));
    }

    private static byte[] base64decode(String property) {
        return Base64.getDecoder().decode(property);
    }
}
