package com.example.coursework2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserSignInController {

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
    private Button registration_button;
    @FXML
    private Button signinbutton;
    @FXML
    void initialize() {
        registration_button.setOnAction(event->{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserRegistration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        signinbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());
            UserClient userClient = new UserClient();

            if(userClient.signIn(ip_adr,port_,loginfield.getText(),passwordfield.getText()) == 1){
                System.out.println("Такой аккаунт существует вы успешно вошли");
                signinbutton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("UserApp.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            if(userClient.signIn(ip_adr,port_,loginfield.getText(),passwordfield.getText()) == 0){
                System.out.println("Не удалось войти");
            }
        });

    }

}