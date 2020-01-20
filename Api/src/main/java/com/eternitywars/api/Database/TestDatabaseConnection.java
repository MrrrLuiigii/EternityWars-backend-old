package com.eternitywars.api.Database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;

public class TestDatabaseConnection implements IDatabaseConnection
{
    public Connection getDatabaseConnection()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setDatabaseName("eternitywars");

        try
        {
            return (Connection) dataSource.getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
