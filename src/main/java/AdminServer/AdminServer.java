package AdminServer;

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
                                        ArrayList<Admins> admins = d.getProducts();
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
                                    }break;
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
