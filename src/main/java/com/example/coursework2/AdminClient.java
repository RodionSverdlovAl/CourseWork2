package com.example.coursework2;

import java.io.*;
import java.net.Socket;


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


}
