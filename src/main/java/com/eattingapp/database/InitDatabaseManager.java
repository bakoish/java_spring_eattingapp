package com.eattingapp.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabaseManager {

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS users ("
                    + "ID INT NOT NULL AUTO_INCREMENT,"
                    + "EMAIL VARCHAR(45) NOT NULL,"
                    + "PASSWORD VARCHAR(45) NOT NULL,"
                    + "NUMER CHAR(9) NOT NULL,"
                    + "PRIMARY KEY (ID))";
    private static final String CREATE_TABLE_DISHES ="CREATE TABLE IF NOT EXISTS dishes ("
                    + "ID INT NOT NULL AUTO_INCREMENT,"
                    + "NAME VARCHAR(45) NOT NULL,"
                    + "DESCRIPTON VARCHAR(300) NOT NULL,"
                    + "PRICE INT NOT NULL,"
                    + "PRIMARY KEY (ID))";

    private static final String CREATE_ZAMOWIENIA_TABLE =
            "CREATE TABLE IF NOT EXISTS zamowienia ("
                    + "USER_ID INT NOT NULL,"
                    + "DISH_ID INT NOT NULL,"
                    + "FOREIGN KEY(USER_ID) REFERENCES personaladress(ID),"
                    + "FOREIGN KEY(DISH_ID) REFERENCES dishes(ID))";
    private static final String CREATE_ZAMOWIENIA2_TABLE =
            "CREATE TABLE IF NOT EXISTS potwierdzone_zamowienia ("
                    + "USER_ID INT NOT NULL,"
                    + "DISH_ID INT NOT NULL,"
                    + "FOREIGN KEY(USER_ID) REFERENCES personaladress(ID),"
                    + "FOREIGN KEY(DISH_ID) REFERENCES dishes(ID))";
    private static final String CREATE_PERSONALADRESS_TABLE =
            "CREATE TABLE IF NOT EXISTS personaladress ("
                    + "ID INT NOT NULL AUTO_INCREMENT,"
                    + "USERNAME VARCHAR(45) NOT NULL UNIQUE,"
                    + "NAME VARCHAR(45) NOT NULL,"
                    + "SURNAME VARCHAR(45) NOT NULL,"
                    + "TELEPHONE VARCHAR(45) NOT NULL,"
                    + "POSTCODE VARCHAR(45) NOT NULL,"
                    + "ADRESS VARCHAR(45) NOT NULL,"
                    + "PRIMARY KEY (ID))";

    private static final String DROP_TABLE_USERS = "drop table if exists users;";
    private static final String DROP_TABLE_DISHES = "drop table if exists dishes;";
    private static final String DROP_TABLE_ADMIN = "drop table if exists admin;";
    private static final String DROP_TABLE_ZAMOWIENIA = "drop table if exists zamowienia;";
    private static final String DROP_TABLE_ZAMOWIENIA2 = "drop table if exists potwierdzone_zamowienia;";
    private static final String DROP_PERSONALADRESS_TABLE = "drop table if exists personaladress;";
    private static final String INSERT_DISHES1 = "INSERT INTO dishes (NAME, DESCRIPTON,PRICE) VALUES ('Pierogi z miesem','Pierogi z miesem w sosie',10);";
    private static final String INSERT_DISHES2 = "INSERT INTO dishes (NAME, DESCRIPTON,PRICE) VALUES ('Pierogi z serem','Pierogi z serem w sosie',10);";
    private static final String INSERT_DISHES3 = "INSERT INTO dishes (NAME, DESCRIPTON,PRICE) VALUES ('Pierogi ruskie','Pierogi ruskie w sosie',10);";


    public static void initStarterTable (Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(DROP_TABLE_USERS);
        stmt.executeUpdate(DROP_TABLE_DISHES);
        stmt.executeUpdate(CREATE_TABLE_SQL);
        stmt.executeUpdate(CREATE_TABLE_DISHES);
        stmt.executeUpdate(INSERT_DISHES1);
        stmt.executeUpdate(INSERT_DISHES2);
        stmt.executeUpdate(INSERT_DISHES3);
        stmt.executeUpdate(DROP_TABLE_ADMIN);
        stmt.executeUpdate(DROP_TABLE_ZAMOWIENIA);
        stmt.executeUpdate(CREATE_ZAMOWIENIA_TABLE);
        stmt.executeUpdate(DROP_PERSONALADRESS_TABLE);
        stmt.executeUpdate(CREATE_PERSONALADRESS_TABLE);
        stmt.executeUpdate(DROP_TABLE_ZAMOWIENIA2);
        stmt.executeUpdate(CREATE_ZAMOWIENIA2_TABLE);

    }
}