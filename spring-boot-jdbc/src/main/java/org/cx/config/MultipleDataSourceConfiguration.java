package org.cx.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author grass
 * @date 2017/10/23
 */
@Configuration
public class MultipleDataSourceConfiguration {

    /**
     * 将方法名注册为bean，需要定义一个primary为主库
     * @return
     */
    @Bean
    @Primary
    public DataSource masterDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        DataSource dataSource = dataSourceBuilder
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test")
                .username("root")
                .password("123123")
                .build();

        return dataSource;
    }

    @Bean
    public DataSource slaveDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        DataSource dataSource = dataSourceBuilder
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test2")
                .username("root")
                .password("123123")
                .build();

        return dataSource;
    }
}
