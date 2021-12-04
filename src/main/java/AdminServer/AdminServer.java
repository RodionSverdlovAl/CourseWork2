package AdminServer;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AdminServer {
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
                                        //System.out.println(admin.getAdmin_firstname() + admin.getAdmin_lastname());
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
