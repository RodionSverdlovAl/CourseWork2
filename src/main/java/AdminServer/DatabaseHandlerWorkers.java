package AdminServer;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Worker> getWorker() {
        ArrayList<Worker> worker = new ArrayList<Worker>();
        String select = "SELECT * FROM " + ConstWorker.WORKER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Worker w =new Worker();

                w.setWorker_name(resSet.getString("name"));
                w.setWorker_surname(resSet.getString("surname"));
                w.setWorker_fathername(resSet.getString("fathername"));
                w.setWorker_departament(resSet.getString("departament"));
                w.setWorker_position(resSet.getString("position"));
                w.setWorker_salary(resSet.getString("salary"));
                w.setWorker_year(resSet.getString("year"));
                w.setWorker_id(resSet.getString("id"));
                worker.add(w);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return worker;
    }


}
