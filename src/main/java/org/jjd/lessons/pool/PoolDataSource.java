package org.jjd.lessons.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolDataSource {
    private static ComboPooledDataSource cpds =
            new ComboPooledDataSource();

    /*static {
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/lessons");
    }*/

    public static Connection getConnection(){
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить" +
                    " соединение " + e.getMessage());
        }
    }
}
