package AdminServer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMethods {
    public String LoginAdmin(String loginText, String loginPassword){
        DatabaseHandler dbHandler = new DatabaseHandler();
        Admins admin = new Admins();
        admin.setAdmin_login(loginText);
        admin.setAdmin_password(loginPassword);
       ResultSet result =  dbHandler.getAdmin(admin);

       int counter = 0;

       try{
        while(result.next()){
           counter++;
        }
       }catch (SQLException e){
           e.printStackTrace();
       }
       if(counter >=1){
           String singinlogs = "success";
           return singinlogs;
       }else{
           String singinlogs = "not success";
           return singinlogs;
       }
    }

    public String AccountingCheck(String worker_id){
        DatabaseHandlerAccounts dbHandler = new DatabaseHandlerAccounts();
        ResultSet result =  dbHandler.getAccounting(worker_id);
        int counter = 0;
        try{
            while(result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >=1){
            String success = "success";
            return success;
        }else{
            String success = "not success";
            return success;
        }
    }
}
