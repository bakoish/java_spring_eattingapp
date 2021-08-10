package com.eattingapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionMenager {
    public static Connection getConnectionManager() throws SQLException {

        Properties myProp = new Properties();
        return DriverManager.getConnection("jdbc:h2:file:/home/student/G1B_FoodOrderApp/src/main/resources/data/users", myProp);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
