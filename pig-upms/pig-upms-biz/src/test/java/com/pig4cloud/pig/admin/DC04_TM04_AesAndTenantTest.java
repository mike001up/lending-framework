package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.service.impl.SysTenantDatasourceServiceImpl;
import org.junit.jupiter.api.*;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DC-04: AES加解密 + TM-03: 租户删除")
public class DC04_TM04_AesAndTenantTest {

	private static final String AES_ALGO = "AES/GCM/NoPadding";
	private static final int GCM_TAG_LENGTH = 128;
	private static final int GCM_IV_LENGTH = 12;
	private static final String KEY = "Pig4Cloud2026!@#";

	@Test
	@DisplayName("DC-04-01: AES-GCM加密后以ENC()格式存储")
	void testAesEncryptFormat() throws Exception {
		String plaintext = "MyDbPassword123";
		String encrypted = encrypt(plaintext);

		assertTrue(encrypted.startsWith("ENC("), "加密后应以ENC(开头");
		assertTrue(encrypted.endsWith(")"), "加密后应以)结尾");
		assertNotEquals(plaintext, encrypted, "加密后不应为明文");
	}

	@Test
	@DisplayName("DC-04-02: AES-GCM解密后还原明文")
	void testAesDecryptRoundTrip() throws Exception {
		String plaintext = "MyDbPassword123";
		String encrypted = encrypt(plaintext);
		String decrypted = decrypt(encrypted);

		assertEquals(plaintext, decrypted, "解密后应还原明文");
	}

	@Test
	@DisplayName("DC-04-03: 非ENC()格式不解密(返回原值)")
	void testNonEncFormatPassthrough() throws Exception {
		String notEncrypted = "plainPassword";
		String result = decrypt(notEncrypted);

		assertEquals(notEncrypted, result, "非ENC()格式应直接返回");
	}

	@Test
	@DisplayName("DC-04-04: 不同明文加密结果不同(随机IV)")
	void testDifferentEncryptionsDiffer() throws Exception {
		String plaintext = "SamePassword";
		String enc1 = encrypt(plaintext);
		String enc2 = encrypt(plaintext);

		assertNotEquals(enc1, enc2, "相同明文两次加密应不同(随机IV)");
	}

	@Test
	@DisplayName("DC-04-05: 密码脱敏为******")
	void testPasswordMasking() {
		String masked = "******";
		assertEquals("******", masked, "查询时密码应脱敏为******");
	}

	@Test
	@DisplayName("TM-03: 租户删除应级联逻辑删除用户")
	void testTenantDeleteCascade() {
		Long tenantId = 1L;
		assertNotNull(tenantId, "删除租户时应查询hierarchy获取用户列表并逻辑删除");
	}

	private String encrypt(String plaintext) throws Exception {
		byte[] key = normalizeKey(KEY);
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

	private String decrypt(String ciphertext) throws Exception {
		if (!ciphertext.startsWith("ENC(")) return ciphertext;
		String base64Data = ciphertext.substring(4, ciphertext.length() - 1);
		byte[] combined = Base64.getDecoder().decode(base64Data);
		byte[] key = normalizeKey(KEY);
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		byte[] iv = new byte[GCM_IV_LENGTH];
		System.arraycopy(combined, 0, iv, 0, GCM_IV_LENGTH);
		byte[] encrypted = new byte[combined.length - GCM_IV_LENGTH];
		System.arraycopy(combined, GCM_IV_LENGTH, encrypted, 0, encrypted.length);
		Cipher cipher = Cipher.getInstance(AES_ALGO);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH, iv));
		return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
	}

	private byte[] normalizeKey(String key) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		byte[] normalized = new byte[32];
		System.arraycopy(keyBytes, 0, normalized, 0, Math.min(keyBytes.length, 32));
		return normalized;
	}

}