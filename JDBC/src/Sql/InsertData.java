package Sql;

import Db_Config.DatabaseConfig;
import java.sql.*;

public class InsertData {
    public static void main(String[] args) throws SQLException {
        String jdbcurl = DatabaseConfig.JDBC_URL;
        String username = DatabaseConfig.USERNAME;
        String password = DatabaseConfig.PASSWORD;

        //Database table and column names
        String tableName = "new_table";
        String nameColumn = "name";
        String ageColumn = "age";

        //Data to insert
        String name = "Rare";
        int age = 220;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(jdbcurl, username, password);

            //prepare sql statement
            String sql = "INSERT INTO " + tableName + " (" + nameColumn + ", " + ageColumn + ") VALUES (?,?)";
            statement = connection.prepareStatement(sql);

            //set values for placeholder
            statement.setString(1, name);
            statement.setInt(2, age);

            //execute the Insert statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully");
                System.out.println("Rows affected: " + rowsAffected);
            } else {
                System.out.println("Record not inserted successfully");
            }
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
}
