package Database;

import AdminServer.Configs;
import Const.ConstUsers;
import Clasess.Users;

import java.sql.*;

public class DatabaseHandlerUsers extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(Users user) {
        String insert = "INSERT INTO " + ConstUsers.USER_TABLE + "(" +
                ConstUsers.USER_NAME + "," + ConstUsers.USER_SURNAME + "," +
                ConstUsers.USER_LOGIN + "," + ConstUsers.USER_PASSWORD + "," +
                ConstUsers.USER_EMAIL +"," + ConstUsers.USER_GENDER +"," + ConstUsers.USER_LOCATION + ")" +
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUser_firstname());
            prSt.setString(2, user.getUser_lastname());
            prSt.setString(3, user.getUser_login());
            prSt.setString(4, user.getUser_password());
            prSt.setString(5, user.getUser_email());
            prSt.setString(6, user.getUser_gender());
            prSt.setString(7, user.getUser_location());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(Users user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstUsers.USER_TABLE + " WHERE " +
                ConstUsers.USER_LOGIN + "=? AND "+ConstUsers.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUser_login());
            prSt.setString(2,user.getUser_password());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }


}
