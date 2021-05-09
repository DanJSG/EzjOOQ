package com.jsg.jooqplayground.db;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionPool {

    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    private static boolean configured = false;

    public static void configure(String connectionString, String username, String password) {
        DATA_SOURCE.setUrl(connectionString);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
        // Set the total number of active connections
        DATA_SOURCE.setMaxTotal(200); // 200 active connections
        // Set the total number of idle (inactive) connections
        DATA_SOURCE.setMaxIdle(150); // 150 connections
        // Set the number of idle connections to always keep open
        DATA_SOURCE.setMinIdle(10); // 10 connections
        // Set the maximum SQL prepared statements that can be open simultaneously
        DATA_SOURCE.setMaxOpenPreparedStatements(200); // 200 statements
        // Set the max wait time before the connection request times out
        DATA_SOURCE.setMaxWaitMillis(2500); // 2.5 seconds
        // Set the time to wait before removing abandoned database connections
        DATA_SOURCE.setRemoveAbandonedTimeout(60); // 60 seconds
        // Sets the minimum amount of time a connection can be idle for
        DATA_SOURCE.setMinEvictableIdleTimeMillis(30000); // 30 seconds
        // Enable removing abandoned connections
        DATA_SOURCE.setRemoveAbandonedOnBorrow(true);
        // The time to wait between evicting idle connections
        DATA_SOURCE.setTimeBetweenEvictionRunsMillis(30000); // 30 seconds
        configured = true;
    }

    public static Connection getConnection() throws SQLException, IllegalStateException {
        if(!configured) throw new IllegalStateException("Connection pool not configured. Please call the configure method once globally before retrieving a connection.");
        return DATA_SOURCE.getConnection();
    }

    public static DataSource getDataSource() {
        if(!configured) throw new IllegalStateException("Connection pool not configured. Please call the configure method once globally before retrieving a connection or data source.");
        return DATA_SOURCE;
    }

}
