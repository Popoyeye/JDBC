package Sql;

import Db_Config.DatabaseConfig;
import java.sql.*;

public class Tester {
    public static void main(String[] args) throws SQLException {
        //database name
        String dbName = "new_db";

        //table name and columns
        String tableName = "new_table";
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + tableName
                + " ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(50) NOT NULL,"
                + "age INT NOT NULL"
                + ")";

        Connection connection = null;
        Statement statement = null;

        try {
            //Access connection details from DataBaseConfig file
            String jdbcurl = DatabaseConfig.JDBC_URL;
            String username = DatabaseConfig.USERNAME;
            String password = DatabaseConfig.PASSWORD;

            //connect to the database
            connection = DriverManager.getConnection(jdbcurl, username, password);

            //create a statement object (can use PreparedStatements)
            statement = connection.createStatement();

            //check if the db exists
            boolean dbExists = checkDatabaseExists(connection, dbName);

            if (!dbExists) {
                System.out.println("Database '" + dbName + "' does not exist. Creating...");
                statement.execute("CREATE DATABASE " + dbName);
                System.out.println("Database '" + dbName + "' has been created");
            } else {
                System.out.println("Database '" + dbName + "' already exist");
            }

            //create the table
            statement.execute(createTableSql);
            System.out.println("Table '" + tableName + "' has been created");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static boolean checkDatabaseExists(Connection connection, String dbName) throws SQLException{
        String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dbName);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
