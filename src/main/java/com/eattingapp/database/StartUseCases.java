package com.eattingapp.database;

import java.sql.Connection;
import java.sql.SQLException;

public class StartUseCases {

    private static Connection connection;
    private static Connection connection2;

    public StartUseCases() {
        try {

            connection = ConnectionMenager.getConnectionManager();
            //connection.setAutoCommit(false); //blokuje pozniejsze commity czyli insert itp..
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            connection2 = ConnectionMenager.getConnectionManager();
            //connection2.setAutoCommit(false);
            connection2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            InitDatabaseManager.initStarterTable(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void initFillDatabase(String[] args) throws SQLException {

        new StartUseCases();
        CRUD.insertUsers(connection2);
        CRUD.getAllUsers(connection);
    }




}