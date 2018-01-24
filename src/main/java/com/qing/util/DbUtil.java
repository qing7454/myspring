package com.qing.util;

import com.qing.io.Resource;
import com.qing.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.util</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class DbUtil {

    private static ThreadLocal<Connection> pools = new ThreadLocal<>();

    private static ResourceLoader resourceLoader = new ResourceLoader();

    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static String driver = "";

    static {
        try {
            Resource resource = resourceLoader.getResource("db.properties");
            InputStream in = resource.getInputStream();
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection open() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            pools.set(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
        Connection connection = pools.get();
        if (null != connection) {
            try {
                connection.close();
                pools.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = open();
        System.out.println(connection);
        close();
    }
}
