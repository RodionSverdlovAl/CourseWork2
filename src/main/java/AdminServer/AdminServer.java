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
