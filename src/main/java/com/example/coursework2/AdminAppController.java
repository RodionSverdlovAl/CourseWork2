package com.example.coursework2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField AdminLastnamefield;

    @FXML
    private TextField AdminNameInput;

    @FXML
    private Button SignUpAdminButton;

    @FXML
    private TextField adminemailfield;

    @FXML
    private TextField adminloginfield;

    @FXML
    private TextField adminpasswordfield;


    @FXML
    void initialize() {

        SignUpAdminButton.setOnAction(event->{

            AdminClient admin_client = new AdminClient();
            admin_client.AddAdmin(AdminNameInput.getText(),AdminLastnamefield.getText(),
                    adminloginfield.getText(),adminpasswordfield.getText(),adminemailfield.getText());

        });

    }

}
