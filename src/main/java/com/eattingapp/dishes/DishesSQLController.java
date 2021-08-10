package com.eattingapp.dishes;

import com.eattingapp.Zamowienia;
import com.eattingapp.database.ConnectionMenager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishesSQLController {
    private static Connection connection;
    public static List createDishes(Connection connection) throws SQLException {

        try{

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql     = "SELECT * FROM dishes";
        Statement stmt = connection.createStatement();
        ResultSet rs   = stmt.executeQuery(sql);


        ResultSetMetaData metadata = rs.getMetaData();
        int numberOfColumns = metadata.getColumnCount();
        List<Dishes> list = new ArrayList<>(numberOfColumns);
        while (rs.next()){
            long id=rs.getInt("ID");
            String name=rs.getString("NAME");
            String description=rs.getString("DESCRIPTON");
            int cena=rs.getInt("PRICE");
            list.add(new Dishes(id,name,description,cena));
        }
        connection.close();
        return list;

    }
    public static Dishes addDishesToDatabase(Dishes dishes) throws SQLException {
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement = connection.prepareStatement("INSERT INTO dishes (NAME, DESCRIPTON,PRICE) VALUES ( ?, ?,?)");
        statement.setString(1, dishes.getName());
        statement.setString(2, dishes.getDescription() );
        statement.setInt(3,dishes.getPrice());
        statement.execute();
        return dishes;
    }
    public static List getDishes()throws SQLException{
        return createDishes(connection);
    }
    public static Dishes getDish(Dishes dishes)throws SQLException{

        return new Dishes(dishes.getID(),dishes.getName(),dishes.getDescription(),dishes.getPrice());

    }
    public static void deleteDishesFromDatabase(Dishes dish) throws SQLException {
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement statement = connection.prepareStatement("DELETE FROM dishes WHERE NAME= ? ");
        statement.setString(1, dish.getName());
        //System.out.println(dish.getName());
        statement.execute();
    }
    public static void editDishesFromDatabase(Dishes dish) throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dishes WHERE NAME= ? ");
        statement.setString(1, dish.getName());
        //System.out.println(dish.getName());
        statement.execute();

    }
    public static void updateDishesinDatabase(Dishes dishes)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(dishes.getName());
        PreparedStatement statement = connection.prepareStatement("UPDATE dishes SET DESCRIPTON = ? ,PRICE=? WHERE NAME=?");
        statement.setString(1,dishes.getDescription());
        statement.setInt(2,dishes.getPrice());
        statement.setString(3, dishes.getName());
        statement.execute();

    }
    public static Dishes dodajDoZamówienia(Dishes dish,String username)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dishes WHERE NAME=?");
        statement.setString(1,dish.getName());
        //System.out.println(dish.getName());
        ResultSet rs=statement.executeQuery();
        Dishes dish2=new Dishes();
        while ( rs.next() )
        {
            dish2.setID(rs.getInt("ID"));
            dish2.setName(rs.getString("NAME"));
            dish2.setDescription(rs.getString("DESCRIPTON"));
            dish2.setPrice(rs.getInt("PRICE"));

        }
        //System.out.println(dish2);
        //System.out.println(username);
        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM personaladress WHERE USERNAME=?");
        statement2.setString(1,username);
        ResultSet rs2=statement2.executeQuery();
        int id=0;
        while ( rs2.next() )
        {
            id=rs2.getInt("ID");
        }
        PreparedStatement statement3 = connection.prepareStatement("INSERT INTO zamowienia (USER_ID,DISH_ID) VALUES (?,?);");
        statement3.setInt(1,id);
        statement3.setInt(2,(int)dish2.getID());
        statement3.execute();


        return dish2;

    }

    public static Integer TotalPrice(String username)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM personaladress WHERE USERNAME=?");
        statement1.setString(1,username);
        ResultSet rs1=statement1.executeQuery();
        int id=0;
        while ( rs1.next() )
        {
            id=rs1.getInt("ID");
        }
        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM zamowienia WHERE USER_ID=?");
        statement2.setInt(1,id);
        ResultSet rs2=statement2.executeQuery();
        //Pełna cena, która będzie wyświetlana
        int totalPrice=0;
        while ( rs2.next() )
        {
            int id2=rs2.getInt("DISH_ID");
            PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM dishes WHERE ID=?");
            statement3.setInt(1,id2);
            ResultSet rs3=statement3.executeQuery();

            while ( rs3.next() )
                totalPrice =totalPrice+rs3.getInt("PRICE");
        }

        return totalPrice;
    }

    public static List ZamowienieLista(String username)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Dishes> Zamowienia=new ArrayList<>();
        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM personaladress WHERE USERNAME=?");
        statement1.setString(1,username);
        ResultSet rs1=statement1.executeQuery();
        int id=0;
        while ( rs1.next() )
        {
            id=rs1.getInt("ID");
        }
        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM zamowienia WHERE USER_ID=?");
        statement2.setInt(1,id);
        ResultSet rs2=statement2.executeQuery();
        while ( rs2.next() )
        {
            int id2=rs2.getInt("DISH_ID");
            PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM dishes WHERE ID=?");
            statement3.setInt(1,id2);
            ResultSet rs3=statement3.executeQuery();
            while ( rs3.next() )
            {
                Zamowienia.add(new Dishes(rs3.getInt("ID"),rs3.getString("NAME"),rs3.getString("DESCRIPTON"),rs3.getInt("PRICE")));

            }
        }


        return Zamowienia;
    }
    public static void UsunZamowienie(String username)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM personaladress WHERE USERNAME=?");
        statement1.setString(1,username);
        ResultSet rs1=statement1.executeQuery();
        int id=0;
        while ( rs1.next() )
        {
            id=rs1.getInt("ID");
        }
        PreparedStatement statement2=connection.prepareStatement("DELETE FROM zamowienia WHERE USER_ID = ?");
        statement2.setInt(1,id);
        statement2.execute();

    }
    public static void DodajZamowienie(String username)throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM personaladress WHERE USERNAME=?");
        statement1.setString(1,username);
        ResultSet rs1=statement1.executeQuery();
        int id=0;
        while ( rs1.next() )
        {
            id=rs1.getInt("ID");
        }
        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM zamowienia WHERE USER_ID=?");
        statement2.setInt(1,id);
        ResultSet rs2=statement2.executeQuery();
        while ( rs2.next() )
        {
            int userid=rs2.getInt("USER_ID");
            int dishid=rs2.getInt("DISH_ID");
            PreparedStatement statement3 = connection.prepareStatement("INSERT INTO potwierdzone_zamowienia (USER_ID,DISH_ID) VALUES (?,?);");
            statement3.setInt(1,userid);
            statement3.setInt(2,dishid);
            statement3.execute();

        }
        UsunZamowienie(username);

    }

    public static List adminOrderList() throws SQLException{
        try {

            connection = ConnectionMenager.getConnectionManager();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Integer> user=new ArrayList<>();
        List<Integer> dishes=new ArrayList<>();

        PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM potwierdzone_zamowienia");
        ResultSet rs1=statement1.executeQuery();
        while ( rs1.next() )
        {
            user.add(rs1.getInt("USER_ID"));
            dishes.add(rs1.getInt("DISH_ID"));
        }

        List<String> user_s=new ArrayList<>();
        List<String> dishes_s=new ArrayList<>();

        List<Zamowienia> zamowienia=new ArrayList<>();
        for(int i=0; i<user.size(); i++){
           // System.out.println(user.get(i));

            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM personaladress WHERE ID=?");
            Zamowienia z=new Zamowienia();
            statement2.setInt(1,user.get(i));

            ResultSet rs2=statement2.executeQuery();

            while ( rs2.next() )
            {
                user_s.add(rs2.getString("ADRESS"));
            }

            PreparedStatement statement3 = connection.prepareStatement("SELECT * FROM dishes WHERE ID=?");
            statement3.setInt(1,dishes.get(i));
            ResultSet rs3=statement3.executeQuery();

            while ( rs3.next() )
            {
                dishes_s.add(rs3.getString("NAME"));

            }

        }

        for (int i=0; i<user.size(); i++)
        {
           // System.out.println(user_s.get(i));
           // System.out.println(dishes_s.get(i));
            zamowienia.add(new Zamowienia(user_s.get(i), dishes_s.get(i)));
        }

        return zamowienia;

    }

}

