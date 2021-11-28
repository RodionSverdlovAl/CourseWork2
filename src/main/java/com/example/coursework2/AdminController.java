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
        connectbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());
        });

    }

}



