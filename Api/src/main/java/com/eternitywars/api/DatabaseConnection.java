package com.eternitywars.api;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseConnection
{
    public DatabaseConnection()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("eternitywars");


    }
}
