package com.pig4cloud.pig.flowable.config;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@DS("flowable")
@EnableTransactionManagement
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration config) {
        config.setDatabaseSchemaUpdate("true"); // 自动建表
    }

    @Value("${spring.datasource.dynamic.datasource.pig-flowable.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.dynamic.datasource.pig-flowable.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.dynamic.datasource.pig-flowable.username}")
    private String username;

    @Value("${spring.datasource.dynamic.datasource.pig-flowable.password}")
    private String password;

    @Bean(name = "flowable")
    public DataSource flowableDataSource() {
        log.info("开始创建 Flowable 数据源...");
        log.info("JDBC URL: {}", jdbcUrl);
        log.info("Driver: {}", driverClassName);
        log.info("Username: {}", username);
        // 检查必要属性是否为空
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            log.error("JDBC URL 不能为空!");
            return null;
        }
        if (driverClassName == null || driverClassName.isEmpty()) {
            log.error("驱动类名不能为空!");
            return null;
        }
        try {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setMaximumPoolSize(10);
            dataSource.setMinimumIdle(2);
            dataSource.setIdleTimeout(30000);
            dataSource.setConnectionTimeout(30000);
            dataSource.setPoolName("FlowableHikariPool");
            // 测试连接
            try {
                dataSource.getConnection().close();
                log.info("Flowable 数据源创建成功并连接测试通过");
            } catch (Exception e) {
                log.error("Flowable 数据源连接测试失败: {}", e.getMessage());
                throw new RuntimeException("数据源连接测试失败", e);
            }
            return dataSource;
        } catch (Exception e) {
            log.error("创建 Flowable 数据源时发生异常: {}", e.getMessage(), e);
            return null;
        }
    }


    @Bean(name = "flowableTransactionManager")
    public PlatformTransactionManager flowableTransactionManager(@Qualifier("flowable") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(@Qualifier("flowable") DataSource dataSource, @Qualifier("flowableTransactionManager") PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        config.setDataSource(dataSource);
        config.setTransactionManager(transactionManager);
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        config.setAsyncExecutorActivate(false);
        config.setDatabaseType("mysql");
        return config;
    }

    @Bean
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> customIdGenerator() {
        return engineConfiguration -> engineConfiguration.setIdGenerator(new StrongUuidGenerator());
    }

}

