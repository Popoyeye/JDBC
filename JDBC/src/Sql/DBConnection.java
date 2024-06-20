package Sql;

import Db_Config.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) throws SQLException {
        String jdbcurl = DatabaseConfig.JDBC_URL;
        String username = DatabaseConfig.USERNAME;
        String password = DatabaseConfig.PASSWORD;

        Connection connection = DriverManager.getConnection(jdbcurl, username, password);

        if (connection != null) {
            System.out.println("Connection successful");
        } else {
            System.out.println("Connection failed");
        }

        //close connection
        connection.close();
    }
}
