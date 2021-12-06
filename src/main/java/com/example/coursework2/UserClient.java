package com.example.coursework2;

import java.io.*;
import java.net.Socket;

public class UserClient {

    void AddUser(String name, String surname, String login, String password,String email,String gender, String location){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("addUser");writer.newLine();

            // отправляем имя фамилию логин пороль и емаил на сервер
            writer.write(name);writer.newLine();
            writer.write(surname);writer.newLine();
            writer.write(login);writer.newLine();
            writer.write(password);writer.newLine();
            writer.write(email);writer.newLine();
            writer.write(gender);writer.newLine();
            writer.write(location);writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
