package com.example.coursework2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button connectbutton;

    @FXML
    private TextField ip_adress;

    @FXML
    private TextField loginfield;

    @FXML
    private TextField passwordfield;

    @FXML
    private TextField port;

    @FXML
    private Button signinbutton;

    @FXML
    void initialize() {

        AdminClient client = new AdminClient();

        connectbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());
            System.out.println(client.Check_Connect(ip_adr,port_));
        });

        signinbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());


            if(client.signIn(ip_adr,port_,loginfield.getText(),passwordfield.getText()) == 1){
                System.out.println("Такой аккаунт существует вы успешно вошли");
            }
            if(client.signIn(ip_adr,port_,loginfield.getText(),passwordfield.getText()) == 0){
                System.out.println("Не удалось войти");
            }




        });

    }

}



