package AdminServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpAdmin(Admins admin) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.FIRST_NAME + "," + Const.LAST_NAME + "," +
                Const.LOGIN + "," + Const.PASSWORD + "," +
                Const.EMAIL + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, admin.getAdmin_firstname());
            prSt.setString(2, admin.getAdmin_lastname());
            prSt.setString(3, admin.getAdmin_login());
            prSt.setString(4, admin.getAdmin_password());
            prSt.setString(5, admin.getAdmin_email());


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAdmin(Admins admin) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
        Const.LOGIN + "=? AND "+Const.PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
             prSt.setString(1,admin.getAdmin_login());
            prSt.setString(2,admin.getAdmin_password());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

   /* public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        String select = "SELECT * FROM " + Const.USER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Product p = new Product();
                p.setProduct_name(resSet.getString("Name"));
                p.setProduct_country(resSet.getString("Country"));
                p.setProduct_type(resSet.getString("type"));
                p.setProduct_maker(resSet.getString("maker"));
                p.setProduct_count(resSet.getString("count"));
                p.setProduct_price(resSet.getString("price"));
                p.setProduct_id(resSet.getString("id"));

                products.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }*/

}