package Database;

import Clasess.Accounting;
import AdminServer.Configs;
import Clasess.AccountingWorkers;
import Clasess.Salary;
import Clasess.Worker;
import Const.ConstAccounts;
import Const.ConstWorker;

import java.sql.*;
import java.util.ArrayList;

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

    public void Accounting_add_rebuke(String id){
        String update = "UPDATE " + ConstAccounts.ACCOUNTS_TABLE +
                " SET " + ConstAccounts.ACCOUNTS_REBUKE + "=?"+ " WHERE " + ConstAccounts.ACCOUNTS_IDWORKER + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, "есть");
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Accounting_delete_rebuke(String id){
        String update = "UPDATE " + ConstAccounts.ACCOUNTS_TABLE +
                " SET " + ConstAccounts.ACCOUNTS_REBUKE + "=?"+ " WHERE " + ConstAccounts.ACCOUNTS_IDWORKER + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, "нет");
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    public void Accounting_bonus(Integer bonus, String id){
        String update = "UPDATE " + ConstAccounts.ACCOUNTS_TABLE +
                " SET " + ConstAccounts.ACCOUNTS_BONUS + "=?"+ " WHERE " + ConstAccounts.ACCOUNTS_IDWORKER + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setInt(1, bonus);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void Accounting_add_hour(Integer hour, String id){
        // сначало достаем значение из таблицы
        Integer HOUR;
        String select = "SELECT " +"hour"+ " FROM " + ConstAccounts.ACCOUNTS_TABLE + " WHERE " +
                ConstAccounts.ACCOUNTS_IDWORKER + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,id);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()){
                Accounting accounting = new Accounting();
                accounting.setAcc_hour(resSet.getString("hour"));
                HOUR = Integer.parseInt(accounting.getAcc_hour()) +hour;
                System.out.println(HOUR);
                String update = "UPDATE " + ConstAccounts.ACCOUNTS_TABLE +
                        " SET " + ConstAccounts.ACCOUNTS_HOUR + "=?"+ " WHERE " + ConstAccounts.ACCOUNTS_IDWORKER + "=?";
                try {
                    PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
                    preparedStatement.setInt(1, HOUR);
                    preparedStatement.setString(2, id);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Salary> getWorkerSalary(){
        ArrayList<Salary> salary = new ArrayList<Salary>();
        String select ="SELECT workers.id, workers.name, workers.surname, workers.departament, workers.salary, accounts.hour, accounts.bonus,accounts.rebuke FROM coursework.workers JOIN accounts ON accounts.worker_id=workers.id;";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Salary sw = new Salary();
                sw.setWorker_id(resSet.getString("id"));
                sw.setWorker_name(resSet.getString("name"));
                sw.setWorker_surname(resSet.getString("surname"));
                sw.setWorker_departament(resSet.getString("departament"));
                //sw.setWorker_salary(resSet.getString("salary"));
                sw.setAcc_hour(resSet.getString("hour"));
                sw.setAcc_bonus(resSet.getString("bonus"));
                sw.setAcc_rebuke(resSet.getString("rebuke"));
                Integer sal = Integer.parseInt(resSet.getString("salary"));
                Integer hou = Integer.parseInt(sw.getAcc_hour());
                Integer persent = (Integer.parseInt(sw.getAcc_bonus())/100)+1;
                Integer SumSal = 0;
                if(sw.getAcc_rebuke() == "нет"){
                    SumSal = sal*hou*persent;
                }else
                if(sw.getAcc_rebuke() == "есть"){
                    SumSal = sal*hou;
                }
                else{
                    SumSal = sal*hou;
                }
                sw.setWorker_salary(SumSal.toString());
                salary.add(sw);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salary;
    }

    public ArrayList<AccountingWorkers> getAccountingWorkers() {
        ArrayList<AccountingWorkers> worker = new ArrayList<AccountingWorkers>();
        String select = "SELECT workers.name, workers.surname, workers.departament, workers.position, accounts.hour, accounts.bonus,accounts.rebuke FROM coursework.workers JOIN accounts ON accounts.worker_id=workers.id;";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                AccountingWorkers aw = new AccountingWorkers();
                aw.setWorker_name(resSet.getString("name"));
                aw.setWorker_surname(resSet.getString("surname"));
                aw.setWorker_departament(resSet.getString("departament"));
                aw.setWorker_position(resSet.getString("position"));
                aw.setAcc_hour(resSet.getString("hour"));
                aw.setAcc_bonus(resSet.getString("bonus"));
                aw.setAcc_rebuke(resSet.getString("rebuke"));
                worker.add(aw);
                //System.out.println(aw.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return worker;
    }

}


// SELECT workers.name, workers.surname, workers.departament, workers.position, accounts.hour, accounts.bonus,accounts.rebuke FROM coursework.workers JOIN accounts ON accounts.worker_id=workers.id;