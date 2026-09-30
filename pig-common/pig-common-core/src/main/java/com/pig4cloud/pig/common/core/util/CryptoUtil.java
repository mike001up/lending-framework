package com.pig4cloud.pig.common.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CryptoUtil {

    @Value("${pigx.datasource.encrypt-key:Pig4Cloud2026!@#}")
    private String encryptKey;

    private static final String AES_ALGO = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
	private static final int GCM_TAG_LENGTH = 128;

    public String aesGcmEncrypt(String plaintext) { 
        /* 你的逻辑 */ 
        try {
            byte[] keyBytes = encryptKey.getBytes(StandardCharsets.UTF_8);
            byte[] key = new byte[32];
            System.arraycopy(keyBytes, 0, key, 0, Math.min(keyBytes.length, 32));
			// byte[] key = normalizeKey(encryptKey);
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance(AES_ALGO);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] iv = cipher.getIV();
			byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
			byte[] combined = new byte[GCM_IV_LENGTH + encrypted.length];
			System.arraycopy(iv, 0, combined, 0, GCM_IV_LENGTH);
			System.arraycopy(encrypted, 0, combined, GCM_IV_LENGTH, encrypted.length);
			return "ENC(" + Base64.getEncoder().encodeToString(combined) + ")";
		}
		catch (Exception e) {
			throw new RuntimeException("AES加密失败", e);
		}
    }

    public String aesGcmDecrypt(String ciphertext) { 
        /* 解密逻辑 */ 
        if (ciphertext == null || !ciphertext.startsWith("ENC(")) {
			return ciphertext;
		}
		try {
			String base64Data = ciphertext.substring(4, ciphertext.length() - 1);
			byte[] combined = Base64.getDecoder().decode(base64Data);

            byte[] keyBytes = encryptKey.getBytes(StandardCharsets.UTF_8);
            byte[] key = new byte[32];
            System.arraycopy(keyBytes, 0, key, 0, Math.min(keyBytes.length, 32));
			// byte[] key = normalizeKey(encryptKey);
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			byte[] iv = new byte[GCM_IV_LENGTH];
			System.arraycopy(combined, 0, iv, 0, GCM_IV_LENGTH);
			byte[] encrypted = new byte[combined.length - GCM_IV_LENGTH];
			System.arraycopy(combined, GCM_IV_LENGTH, encrypted, 0, encrypted.length);
			Cipher cipher = Cipher.getInstance(AES_ALGO);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH, iv));
			return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
		}
		catch (Exception e) {
			throw new RuntimeException("AES解密失败", e);
		}
    }
    // private byte[] normalizeKey(String key) {
	// 	byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
	// 	byte[] normalized = new byte[32];
	// 	System.arraycopy(keyBytes, 0, normalized, 0, Math.min(keyBytes.length, 32));
	// 	return normalized;
	// }
}
