package com.eattingapp.personaldata;

import com.eattingapp.database.ConnectionMenager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalAdressSQLController {

    private static Connection connection;

    public static PersonalAdress pushPersonalAdress(PersonalAdress personaladress) throws SQLException {
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement = connection.prepareStatement("INSERT INTO personaladress (USERNAME, NAME,SURNAME,TELEPHONE,POSTCODE,ADRESS) VALUES ( ?,?,?,?,?,?)");
        statement.setString(1, personaladress.getUsername());
        statement.setString(2, "brak");
        statement.setString(3, "brak");
        statement.setString(4, "brak");
        statement.setString(5, "brak");
        statement.setString(6, "brak");
        statement.execute();
        return personaladress;
    }

    public static List getPersonalAdress(PersonalAdress personaladress) throws SQLException {

        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM personaladress WHERE USERNAME = '";
        sql += personaladress.getUsername();
        sql += "'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData metadata = rs.getMetaData();
        int numberOfColumns = metadata.getColumnCount();
        List<PersonalAdress> list = new ArrayList<>(numberOfColumns);
        while (rs.next()) {
            long id = rs.getInt("ID");
            String USERNAME = rs.getString("USERNAME");
            String NAME = rs.getString("NAME");
            String SURNAME = rs.getString("SURNAME");
            String TELEPHONE = rs.getString("TELEPHONE");
            String POSTCODE = rs.getString("POSTCODE");
            String ADRESS = rs.getString("ADRESS");
            list.add(new PersonalAdress(id, USERNAME, NAME, SURNAME, TELEPHONE, POSTCODE, ADRESS));
        }
        connection.close();
        return list;

    }

    public static void editPersonalAdressFun(PersonalAdress personaladress) throws SQLException {
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM personaladress WHERE USERNAME =?");
        statement.setString(1, personaladress.getUsername());
        statement.execute();

    }

    public static void updatePersonalAdressFun(PersonalAdress personaladress)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement statement = connection.prepareStatement("UPDATE personaladress SET NAME = ? , SURNAME = ?,TELEPHONE=?,POSTCODE=?,ADRESS=? WHERE USERNAME=?");
        statement.setString(1,personaladress.getName());
        statement.setString(2,personaladress.getSurname());
        statement.setString(3,personaladress.getTelephone());
        statement.setString(4,personaladress.getPostcode());
        statement.setString(5,personaladress.getAdress());
        statement.setString(6,personaladress.getUsername());
        statement.execute();

    }


}
