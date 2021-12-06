package AdminServer;

import java.sql.*;

public class DatabaseHandlerAccounts extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ResultSet getAccounting(String worker_id) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstAccounts.ACCOUNTS_TABLE + " WHERE " +
                ConstAccounts.ACCOUNTS_IDWORKER + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,worker_id);

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void Accounting_add_worker(String worker_id){
        String insert = "INSERT INTO " + ConstAccounts.ACCOUNTS_TABLE + "(" +
                ConstAccounts.ACCOUNTS_IDWORKER + "," + ConstAccounts.ACCOUNTS_HOUR + "," +
                ConstAccounts.ACCOUNTS_BONUS + "," + ConstAccounts.ACCOUNTS_REBUKE + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, worker_id);
            prSt.setString(2, "0");
            prSt.setString(3, "0");
            prSt.setString(4, "нет");
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
