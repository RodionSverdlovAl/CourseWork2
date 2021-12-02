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

    public ArrayList<Admins> getAdmins() {
        ArrayList<Admins> products = new ArrayList<Admins>();
        String select = "SELECT * FROM " + Const.USER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Admins p = new Admins();
                p.setAdmin_firstname(resSet.getString("firstname"));
                p.setAdmin_lastname(resSet.getString("lastname"));
                p.setAdmin_login(resSet.getString("login"));
                p.setAdmin_password(resSet.getString("password"));
                p.setAdmin_email(resSet.getString("email"));
                p.setAdmin_id(resSet.getString("id"));

                products.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void DeleteAdmin(String id){
        String delete = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.ADMIN_ID + "='" + id + "'; ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
            System.out.println("id принятое на сервер = " + id);
            System.out.println("Админ успешно удален");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}