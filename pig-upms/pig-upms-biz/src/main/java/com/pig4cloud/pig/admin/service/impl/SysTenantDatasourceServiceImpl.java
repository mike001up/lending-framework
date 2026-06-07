package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.admin.mapper.SysTenantDatasourceMapper;
import com.pig4cloud.pig.admin.service.SysTenantDatasourceService;
import com.pig4cloud.pig.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class SysTenantDatasourceServiceImpl extends ServiceImpl<SysTenantDatasourceMapper, SysTenantDatasource> implements SysTenantDatasourceService {

	private static final String AES_ALGO = "AES/GCM/NoPadding";
	private static final int GCM_TAG_LENGTH = 128;
	private static final int GCM_IV_LENGTH = 12;

	@Value("${pig.datasource.encrypt-key:Pig4Cloud2026!@#}")
	private String encryptKey;

	@Override
	public boolean save(SysTenantDatasource entity) {
		if (entity.getPasswordEncrypted() != null) {
			entity.setPasswordEncrypted(encrypt(entity.getPasswordEncrypted()));
		}
		return super.save(entity);
	}

	@Override
	public boolean updateById(SysTenantDatasource entity) {
		if (entity.getPasswordEncrypted() != null && !entity.getPasswordEncrypted().startsWith("ENC(")) {
			entity.setPasswordEncrypted(encrypt(entity.getPasswordEncrypted()));
		}
		return super.updateById(entity);
	}

	@Override
	public SysTenantDatasource getByIdWithDecrypted(Long id) {
		SysTenantDatasource ds = baseMapper.selectById(id);
		if (ds != null && ds.getPasswordEncrypted() != null) {
			ds.setPasswordEncrypted(decrypt(ds.getPasswordEncrypted()));
		}
		return ds;
	}

	@Override
	public List<SysTenantDatasource> listByTenantIdWithDecrypted(Long tenantId) {
		List<SysTenantDatasource> list = baseMapper.selectList(Wrappers.<SysTenantDatasource>lambdaQuery()
			.eq(SysTenantDatasource::getTenantId, tenantId));
		list.forEach(ds -> {
			if (ds.getPasswordEncrypted() != null) {
				ds.setPasswordEncrypted(decrypt(ds.getPasswordEncrypted()));
			}
		});
		return list;
	}

	@Override
	public R testConnection(Long id) {
		SysTenantDatasource ds = baseMapper.selectById(id);
		if (ds == null) {
			return R.failed("数据源配置不存在");
		}
		String plainPassword = decrypt(ds.getPasswordEncrypted());
		String url = String.format("jdbc:%s://%s:%d/%s", ds.getDbType(), ds.getHost(), ds.getPort(), ds.getDbName());
		try (Connection conn = DriverManager.getConnection(url, ds.getUsername(), plainPassword)) {
			return R.ok(true);
		}
		catch (Exception e) {
			log.error("数据源连接测试失败: {}", e.getMessage());
			return R.failed("连接测试失败: " + e.getMessage());
		}
	}

	private String encrypt(String plaintext) {
		try {
			byte[] key = normalizeKey(encryptKey);
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

	private String decrypt(String ciphertext) {
		if (ciphertext == null || !ciphertext.startsWith("ENC(")) {
			return ciphertext;
		}
		try {
			String base64Data = ciphertext.substring(4, ciphertext.length() - 1);
			byte[] combined = Base64.getDecoder().decode(base64Data);
			byte[] key = normalizeKey(encryptKey);
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

	private byte[] normalizeKey(String key) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		byte[] normalized = new byte[32];
		System.arraycopy(keyBytes, 0, normalized, 0, Math.min(keyBytes.length, 32));
		return normalized;
	}

}
