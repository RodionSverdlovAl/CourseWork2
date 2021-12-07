package AdminServer;

import Clasess.*;
import Database.DatabaseHandler;
import Database.DatabaseHandlerAccounts;
import Database.DatabaseHandlerUsers;
import Database.DatabaseHandlerWorkers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8081)) { //Создаем сервер
            System.out.println("Server started...");
            while(true)
                try
                {
                    Socket socket =  serverSocket.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try(BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                                BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream())))
                            {
                                System.out.println("try working");
                                String nemu = reader.readLine(); // принимаем пункт меню
                                System.out.println(nemu); // выводим пункт меню в консоль
                                switch (nemu){
                                    case "authorization":{
                                        System.out.println("Вы успешно вошли в кейс авторизации");
                                        String Alogin = reader.readLine(); // принимаем логин
                                        String Apassword = reader.readLine(); // принимаем пороль
                                        ServerMethods s = new ServerMethods();
                                        String SingInSuccess;
                                        SingInSuccess =  s.LoginAdmin(Alogin,Apassword);
                                        writer.write(SingInSuccess); // отправляем успешность вхождения
                                        System.out.println(SingInSuccess);
                                        writer.newLine();
                                        writer.flush();
                                    }break;
                                    case "authorizationUser":{
                                        System.out.println("Вы успешно вошли в кейс авторизации пользователя");
                                        String Alogin = reader.readLine(); // принимаем логин
                                        String Apassword = reader.readLine(); // принимаем пороль
                                        ServerMethods s = new ServerMethods();
                                        String SingInSuccess;
                                        SingInSuccess =  s.LoginUser(Alogin,Apassword);
                                        writer.write(SingInSuccess); // отправляем успешность вхождения
                                        System.out.println(SingInSuccess);
                                        writer.newLine();
                                        writer.flush();
                                    }
                                    case "Accounting_add_worker":{
                                        System.out.println("Вы вошли в кейс добавление работника в учет");
                                        String worker_id = reader.readLine();
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        ServerMethods s = new ServerMethods();
                                        String success = s.AccountingCheck(worker_id);
                                        if(success == "not success"){
                                            d.Accounting_add_worker(worker_id);
                                        }else{
                                            System.out.println("Данный работник уже добавлен в учет деятельности");
                                        }
                                    }break;
                                    case "Accounting_add_rebuke":{
                                        System.out.println("Вы вошли в кейс добавление дисциплинарного взыскания");
                                        String worker_id = reader.readLine();
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_add_rebuke(worker_id);
                                    }break;
                                    case "Accounting_delete_rebuke":{
                                        System.out.println("Вы вошли в кейс добавление удаление взыскания");
                                        String worker_id = reader.readLine();
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_delete_rebuke(worker_id);
                                    }break;
                                    case "Accounting_bonus":{
                                        System.out.println("Вы вошли в кейс добавление премии");
                                        String bonus_p = reader.readLine();
                                        String id = reader.readLine();
                                        Integer bonus = Integer.parseInt(bonus_p);
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_bonus(bonus,id);
                                    }break;
                                    case "Accounting_hour":{
                                        System.out.println("Вы вошли в кейс добавление часов");
                                        String hour = reader.readLine();
                                        String id = reader.readLine();
                                        Integer hours = Integer.parseInt(hour);
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_add_hour(hours,id);
                                    }break;
                                    case "addUser":{
                                        System.out.println("Вы вошли в кейс добавление пользователя");
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String login = reader.readLine();
                                        String password = reader.readLine();
                                        String email = reader.readLine();
                                        String gender = reader.readLine();
                                        String location = reader.readLine();
                                        Users user = new Users(name,surname,login,password,email,gender,location);
                                        DatabaseHandlerUsers d = new DatabaseHandlerUsers();
                                        d.signUpUser(user);
                                    }break;
                                    case "addAdmin":{
                                        System.out.println("Вы вошли в кейс добавление администратора");
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String login = reader.readLine();
                                        String password = reader.readLine();
                                        String email = reader.readLine();
                                        Admins admin = new Admins(name,surname,login,password,email);
                                        DatabaseHandler dbHandler = new DatabaseHandler();
                                        dbHandler.signUpAdmin(admin);
                                        System.out.println(name+surname+login+password+email);
                                    }break;
                                    case "ShowWorkersSalary":{
                                        System.out.println("Вы вошли в кейс просмотр Аналитики зарплат работников");
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        ArrayList<Salary> salary = d.getWorkerSalary();
                                        ArrayList<Salary> arrayList = new ArrayList<>();
                                        System.out.println(salary.get(1));
                                        for(Salary p : salary){
                                            System.out.println(p.toString());
                                            arrayList.add(p);
                                        }
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(arrayList);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    case "ShowAccountingWorkers":{
                                        System.out.println("Вы вошли в кейс просмотр Аналитики работников");
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        ArrayList<AccountingWorkers> accountingWorkers = d.getAccountingWorkers();
                                        System.out.println(accountingWorkers.get(1));
                                        ArrayList<AccountingWorkers> arrayList = new ArrayList<>();
                                        for(AccountingWorkers p : accountingWorkers){
                                            System.out.println(p.toString());
                                            arrayList.add(p);
                                        }
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(arrayList);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "ShowWorkers":{
                                        System.out.println("Вы вошли в кейс просмотр Работников");
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        ArrayList<Worker> workers = d.getWorker();
                                        /*for(Admins p : admins){
                                            System.out.println(p.toString());
                                            System.out.println(p.getAdmin_firstname()+" "+p.getAdmin_lastname());
                                        }*/
                                        //////////////////////////////////////////////
                                        ArrayList<Worker> arrayList =  new ArrayList<Worker>();
                                        for(Worker p : workers){
                                            System.out.println(p.toString());
                                            arrayList.add(p);
                                        }
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(arrayList);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "showusers":{
                                        System.out.println("Вы вошли в кейс просмотр пользователей");
                                        DatabaseHandlerUsers d = new DatabaseHandlerUsers();
                                        ArrayList<Users> users = d.getUsers();
                                        ArrayList<Users> arrayList = new ArrayList<Users>();
                                        for(Users p : users){
                                            arrayList.add(p);
                                        }
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(arrayList);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }break;
                                    case "showadmins": {
                                        System.out.println("Вы вошли в кейс просмотр админов");
                                        DatabaseHandler d = new DatabaseHandler();
                                        ArrayList<Admins> admins = d.getAdmins();
                                        for(Admins p : admins){
                                            System.out.println(p.toString());
                                            System.out.println(p.getAdmin_firstname()+" "+p.getAdmin_lastname());
                                        }
                                        //////////////////////////////////////////////
                                        ArrayList<Admins> arrayList =  new ArrayList<Admins>();
                                        for(Admins p : admins){
                                            System.out.println(p.toString());
                                            arrayList.add(p);
                                        }
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(arrayList);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "deleteWorker":{
                                        System.out.println("Вы вошли в кейс удаление workerov");
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String id = reader.readLine();
                                        d.DeleteWorker(id);
                                    }break;
                                    case "deleteUser":{
                                        System.out.println("Вы вошли в кейс удаление пользователей");
                                        DatabaseHandlerUsers d = new DatabaseHandlerUsers();
                                        String id =reader.readLine();
                                        d.DeleteUser(id);
                                    }break;
                                    case "deleteAccountionWorker":{
                                        System.out.println("Вы вошли в кейс удаление работника из учета");
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        String id = reader.readLine();
                                        d.DeleteAccountingWorker(id);
                                    }break;
                                    case "deleteAdmin":{
                                        System.out.println("Вы вошли в кейс удаление админов");
                                        DatabaseHandler d = new DatabaseHandler();
                                        String id =reader.readLine();
                                        d.DeleteAdmin(id);
                                    }break;
                                    case "FindAdmin":{
                                        System.out.println("Вы вошли в кейс поиск администраторов");
                                        DatabaseHandler d = new DatabaseHandler();
                                        String id =reader.readLine();
                                        Admins admin = d.Find_Admin_For_Edit(id); // достали нужного админа
                                        System.out.println(admin.getAdmin_firstname()+" "+admin.getAdmin_lastname()+" "+admin.getAdmin_login()
                                                +" "+admin.getAdmin_password()+" "+admin.getAdmin_email());
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(admin); // отправляем админа на клиент
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case"FindWorker":{
                                        System.out.println("Вы вошли в кейс поиск работников");
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String id =reader.readLine();
                                        Worker worker = d.Find_Worker_For_Edit(id);
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(worker); // отправляем работника на клиент
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "EditWorker":{
                                        System.out.println("Вы вошли в кейс редактирование Работников");
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String id = reader.readLine();
                                        String Name = reader.readLine();
                                        String Surname = reader.readLine();
                                        String Fathername = reader.readLine();
                                        String Departament = reader.readLine();
                                        String Position = reader.readLine();
                                        String Salary = reader.readLine();
                                        String Year = reader.readLine();
                                        Worker worker = new Worker(Name,Surname,Fathername,Departament,Position,Year,Salary);
                                        d.EditWorker(id,worker);
                                    }break;
                                    case "EditAdmin":{
                                        System.out.println("Вы вошли в кейс редактирование админов");
                                        DatabaseHandler d = new DatabaseHandler();
                                        String id =reader.readLine();
                                        String Name =reader.readLine();
                                        String Surname =reader.readLine();
                                        String Login =reader.readLine();
                                        String Password =reader.readLine();
                                        String Email =reader.readLine();
                                        Admins admin = new Admins(Name, Surname,Login,Password,Email);
                                        d.EditAdmin(id,admin);
                                    }break;
                                    case "AddWorker":{
                                        System.out.println("Вы вошли в кейс добавление работника");
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String fathername = reader.readLine();
                                        String departament = reader.readLine();
                                        String position = reader.readLine();
                                        String salary = reader.readLine();
                                        String year = reader.readLine();
                                        Worker worker =new Worker(name,surname,fathername,departament,position,year,salary);
                                        d.AddWorker(worker);
                                    }
                                }

                            }catch (IOException e){
                                e.printStackTrace();
                            }

                        }
                    }).start();

                }catch (Exception e){
                    e.printStackTrace();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
