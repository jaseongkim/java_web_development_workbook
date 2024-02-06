package org.zerok.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jdk.internal.misc.FileSystemOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTests {

    @Test
    public void testConnection() throws Exception {

        Class.forName("oracle.jdbc.OracleDriver");

        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user ="webuser";
        String password = "webuser";

        Connection connection = DriverManager.getConnection(url, user, password);

        Assertions.assertNotNull(connection);

        connection.close();

    }

    @Test
    public void testHikariCP() throws Exception {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("PrepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);

        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();
    }
}