package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.admin.mapper.SysTenantDatasourceMapper;
import com.pig4cloud.pig.admin.service.SysTenantDatasourceService;
import com.pig4cloud.pig.common.core.util.CryptoUtil;
import com.pig4cloud.pig.common.core.util.R;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SysTenantDatasourceServiceImpl extends ServiceImpl<SysTenantDatasourceMapper, SysTenantDatasource> implements SysTenantDatasourceService {
	private final CryptoUtil cryptoUtil;
	private final ResourceLoader resourceLoader;
	// private static final String AES_ALGO = "AES/GCM/NoPadding";
	// private static final int GCM_TAG_LENGTH = 128;
	// private static final int GCM_IV_LENGTH = 12;

	// @Value("${pig.datasource.encrypt-key:Pig4Cloud2026!@#}")
	// private String encryptKey;

	@Override
	public boolean save(SysTenantDatasource entity) {
		if (entity.getPasswordEncrypted() != null) {
			entity.setPasswordEncrypted(cryptoUtil.aesGcmEncrypt(entity.getPasswordEncrypted()));
		}
		Long count = this.count(Wrappers.<SysTenantDatasource>lambdaQuery().eq(SysTenantDatasource::getTenantId, entity.getTenantId()).eq(SysTenantDatasource::getClientId, entity.getClientId()));
		if(count > 0){
			return false;
		}
		boolean isDbSuc = createDatabase(entity);
		boolean isSQLSuc = executeSqlFile(entity);
		if(!isDbSuc || !isSQLSuc){
			return false;
		}
		return super.save(entity);
	}

	@Override
	public boolean updateById(SysTenantDatasource entity) {
		entity.setDbName(null);
		if (entity.getPasswordEncrypted() != null && !entity.getPasswordEncrypted().startsWith("ENC(")) {
			entity.setPasswordEncrypted(cryptoUtil.aesGcmEncrypt(entity.getPasswordEncrypted()));
		}
		return super.updateById(entity);
	}

	@Override
	public SysTenantDatasource getByIdWithDecrypted(Long id) {
		SysTenantDatasource ds = baseMapper.selectById(id);
		if (ds != null && ds.getPasswordEncrypted() != null) {
			ds.setPasswordEncrypted(cryptoUtil.aesGcmDecrypt(ds.getPasswordEncrypted()));
		}
		return ds;
	}

	@Override
	public List<SysTenantDatasource> listByTenantIdWithDecrypted(Long tenantId) {
		List<SysTenantDatasource> list = baseMapper.selectList(Wrappers.<SysTenantDatasource>lambdaQuery()
			.eq(SysTenantDatasource::getTenantId, tenantId));
		list.forEach(ds -> {
			if (ds.getPasswordEncrypted() != null) {
				ds.setPasswordEncrypted(cryptoUtil.aesGcmDecrypt(ds.getPasswordEncrypted()));
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
		String plainPassword = cryptoUtil.aesGcmDecrypt(ds.getPasswordEncrypted());
		String url = String.format("jdbc:%s://%s:%d/%s", ds.getDbType(), ds.getHost(), ds.getPort(), ds.getDbName());
		try (Connection conn = DriverManager.getConnection(url, ds.getUsername(), plainPassword)) {
			return R.ok(true);
		}
		catch (Exception e) {
			log.error("数据源连接测试失败: {}", e.getMessage());
			return R.failed("连接测试失败: " + e.getMessage());
		}
	}

	private boolean createDatabase(SysTenantDatasource ds) {
		String serverUrl = buildServerUrl(ds.getHost(), ds.getPort());
		try (Connection conn = DriverManager.getConnection(serverUrl, ds.getUsername(), cryptoUtil.aesGcmDecrypt(ds.getPasswordEncrypted()));
			Statement st = conn.createStatement()) {
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + ds.getDbName() + "` " +
					"DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
			return true;
		}catch (Exception e) {
			log.error("执行 创建数据库失败: {}", e);
			return false;
    	}
	}

	private boolean executeSqlFile(SysTenantDatasource ds) {
		String serverUrl = buildDBUrl(ds.getHost(), ds.getPort(), ds.getDbName());
		String sqlFilePath = "classpath:sql/"+ds.getDbName() + ".sql";
		try (Connection conn = DriverManager.getConnection(serverUrl, ds.getUsername(), cryptoUtil.aesGcmDecrypt(ds.getPasswordEncrypted()));
			Statement st = conn.createStatement()) {
			Resource resource = resourceLoader.getResource(sqlFilePath);
			ScriptUtils.executeSqlScript(conn, resource);
			return true;
		}catch (Exception e) {
			log.error("执行 SQL 文件失败: {}", sqlFilePath, e);
			return false;
    	}
	}
	/**
	 * 构造连接 MySQL 服务器的 JDBC URL（不指定 database）
	 * 用于执行 CREATE DATABASE 等服务器级操作
	 */
	private String buildServerUrl(String host, Integer port) {
		if (host == null || host.isBlank()) {
			throw new IllegalArgumentException("host 不能为空");
		}
		int p = (port == null || port <= 0) ? 3306 : port;

		return String.format(
				"jdbc:mysql://%s:%d/?" +
						"useUnicode=true" +
						"&characterEncoding=utf8" +
						"&serverTimezone=Asia/Shanghai" +
						"&useSSL=false" +
						"&allowPublicKeyRetrieval=true" +
						"&connectTimeout=5000" +
						"&socketTimeout=10000",
				host, p
		);
	}
	/**
	 * 构造连接 MySQL 服务器的 JDBC URL（不指定 database）
	 * 用于执行 CREATE DATABASE 等服务器级操作
	 */
	private String buildDBUrl(String host, Integer port, String dbName) {
		if (host == null || host.isBlank()) {
			throw new IllegalArgumentException("host 不能为空");
		}
		int p = (port == null || port <= 0) ? 3306 : port;
		if (dbName == null || dbName.isBlank()) {
			throw new IllegalArgumentException("dbName 不能为空");
		}
		return String.format(
				"jdbc:mysql://%s:%d/%s?" +
						"useUnicode=true" +
						"&characterEncoding=utf8" +
						"&serverTimezone=Asia/Shanghai" +
						"&useSSL=false" +
						"&allowPublicKeyRetrieval=true" +
						"&connectTimeout=5000" +
						"&socketTimeout=10000",
				host, p, dbName
		);
	}
}
