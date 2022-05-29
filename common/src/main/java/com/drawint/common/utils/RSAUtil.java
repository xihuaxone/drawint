package com.drawint.common.utils;

import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import com.drawint.common.exception.BizException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class RSAUtil {
    private static String encrypt(String content, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(content.getBytes());
        return Base64.encodeBase64String(bytes);
    }

    private static String decrypt(String encryptStr, Key key) throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue());
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(encryptStr));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String encryptByPublicKey(String content) {
        try {
            return encrypt(content, SecretKeyUtil.getPublicKey());
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    public static String decryptByPrivateKey(String encryptStr) {
        try {
            return decrypt(encryptStr, SecretKeyUtil.getPrivateKey());
        } catch (Exception e) {
            throw new BizException(e);
        }
    }
}
