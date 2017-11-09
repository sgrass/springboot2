package org.cx.repository;

import org.cx.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.nio.file.FileAlreadyExistsException;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author grass
 * @date 2017/10/22
 */
@Repository
public class UserRepository {

    /**
     * application.properties配置默认数据源
     */
    private final DataSource dataSource;

    /**
     * MultipleDataSourceConfiguration中配置的master库
     */
    private final DataSource masterDataSource;

    private final DataSource slaveDataSource;

    private final JdbcTemplate jdbcTemplate;

    /**
     * platformTransactionManager数据源==datasource
     */
    private final PlatformTransactionManager platformTransactionManager;

    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("slaveDataSource") DataSource slaveDataSource,
                          JdbcTemplate jdbcTemplate, PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    /**
     * jdbc编程式事物
     * @param user
     * @return
     */
    public Boolean jdbcSave(User user) {
        boolean success = false;

        System.out.printf("[Thread : %s ] save user :%s\n",
                Thread.currentThread().getName(), user);

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users(name) values(?);");
            pstmt.setString(1, user.getName());
            success = pstmt.executeUpdate() > 0;

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.commit();
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
        return  success;
    }

    @Transactional
    public Boolean transactionalSave(User user) {
        Boolean success = false;
        success = jdbcTemplate.execute("INSERT INTO users(name) values(?);", new PreparedStatementCallback<Boolean>() {
            @Nullable
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1, user.getName());

               return preparedStatement.executeUpdate() > 0;
            }
        });
        return success;
    }

    public Boolean save(User user) {
        Boolean success = false;
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        success = jdbcTemplate.execute("INSERT INTO users(name) values(?);", new PreparedStatementCallback<Boolean>() {
            @Nullable
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1, user.getName());
                return preparedStatement.executeUpdate() > 0;
            }
        });

        platformTransactionManager.commit(transactionStatus);

        return success;
    }

    public Collection<User> findAll() {
        return Collections.emptyList();
    }
}
