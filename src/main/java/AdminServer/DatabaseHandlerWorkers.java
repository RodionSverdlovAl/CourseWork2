package AdminServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandlerWorkers extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void AddWorker(Worker worker){
        String insert = "INSERT INTO " + ConstWorker.WORKER_TABLE + "(" +
                ConstWorker.WORKER_NAME + "," + ConstWorker.WORKER_SURNAME + "," +
                ConstWorker.WORKER_FATHERNAME + "," + ConstWorker.WORKER_DEPARTAMENT + "," +
                ConstWorker.WORKER_POSITION +","+ConstWorker.WORKER_SALARY + ","+ConstWorker.WORKER_YEAR + ")" +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, worker.getWorker_name());
            prSt.setString(2, worker.getWorker_surname());
            prSt.setString(3, worker.getWorker_fathername());
            prSt.setString(4, worker.getWorker_departament());
            prSt.setString(5, worker.getWorker_position());
            prSt.setString(6, worker.getWorker_salary());
            prSt.setString(7, worker.getWorker_year());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
