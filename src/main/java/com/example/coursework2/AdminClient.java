package com.example.coursework2;

import AdminServer.Admins;
import AdminServer.Worker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class AdminClient {

    private String log;
    private String AdminLogin;
    private String AdminPassword;
    private String ip;
    private int port;


    String Check_Connect(String ip, int port){
        try(Socket clientSocket = new Socket(ip,port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";

        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return log;
    }

    int signIn(String ip, int port, String login, String password){
        this.port  = port;
        this.ip = ip;

        AdminLogin = login;
        AdminPassword = password;
        int status = 0;

        try(Socket clientSocket = new Socket(ip,port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            System.out.println("Client connected...");
            writer.write("authorization");
            writer.newLine();

            // отправляем логин
            writer.write(AdminLogin);
            writer.newLine();

            // отправляем пороль
            writer.write(AdminPassword);
            writer.newLine();

            writer.flush();

            String success = reader.readLine(); // принимаем состояние входа успех или нет


            if(success.equals("success")){
                status = 1;
            }else{
                status = 0;
            }


        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return status;
    }


    void AddAdmin(String name, String surname, String login, String password,String email){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("addAdmin");
            writer.newLine();

            // отправляем имя фамилию логин пороль и емаил на сервер
            writer.write(name);
            writer.newLine();
            writer.write(surname);
            writer.newLine();
            writer.write(login);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.write(email);
            writer.newLine();
            writer.flush();




        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
    }

    ArrayList<Admins> showAdmins(){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("showadmins");
            writer.newLine();
            writer.flush();

            ArrayList<Admins> arrayList = new ArrayList<Admins>();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    arrayList =  (ArrayList<Admins>) object;
                    System.out.println(arrayList.size());
                    for(int i=0; i<arrayList.size(); i++){
                        System.out.println(arrayList.get(i).getAdmin_firstname());
                    }
                    return arrayList;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return null;
    }

    void DeleteAdmin(String id){ // просто передаю id на сервак
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("deleteAdmin");
            writer.newLine();
            writer.write(id);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    Admins FindAdmin(String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("FindAdmin");
            writer.newLine();
            writer.write(id);
            writer.newLine();
            writer.flush();

            Admins admin = new Admins();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    admin =  (Admins) object;
                    System.out.println(admin.getAdmin_firstname());
                    return admin;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    void EditAdmin(String id, String Name, String Surname, String Login, String Password, String Email){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("EditAdmin");
            writer.newLine();
            writer.write(id);
            writer.newLine();
            writer.write(Name);
            writer.newLine();
            writer.write(Surname);
            writer.newLine();
            writer.write(Login);
            writer.newLine();
            writer.write(Password);
            writer.newLine();
            writer.write(Email);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    void AddWorker(Worker worker){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("AddWorker");
            writer.newLine();
            writer.flush();
            try( ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
                objectOutputStream.writeObject(worker); // отправляем работника на сервер
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}
