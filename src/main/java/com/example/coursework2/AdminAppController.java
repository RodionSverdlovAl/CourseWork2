package com.example.coursework2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AdminServer.Admins;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Admins> admintable;

    @FXML
    private Button uploadbutton;

    @FXML
    private TableColumn<Admins, String> viewemail;

    @FXML
    private TableColumn<Admins, String> viewid;

    @FXML
    private TableColumn<Admins, String> viewname;

    @FXML
    private TableColumn<Admins, String> viewsurname;

    @FXML
    private TextField idForDelete;

    @FXML
    private Button DeleteAdminButton;

    private ArrayList<Admins> AdminArrayList  = new ArrayList<>();



    @FXML
    void initialize() {

        SignUpAdminButton.setOnAction(event->{

            AdminClient admin_client = new AdminClient();
            admin_client.AddAdmin(AdminNameInput.getText(),AdminLastnamefield.getText(),
                    adminloginfield.getText(),adminpasswordfield.getText(),adminemailfield.getText());
        });


        uploadbutton.setOnAction(event->{
            AdminClient admin_client = new AdminClient();
            admin_client.showAdmins();
            ArrayList<Admins> adminsArrayList = admin_client.showAdmins();
            this.AdminArrayList = adminsArrayList;
            ObservableList<Admins> observableList = FXCollections.observableArrayList(admin_client.showAdmins());
            admintable.setItems(observableList);
            admintable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("admin_id"));
            admintable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("admin_firstname"));
            admintable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("admin_lastname"));
            admintable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("admin_login"));
            admintable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("admin_email"));
        });

        DeleteAdminButton.setOnAction(event ->{
            String id = idForDelete.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.DeleteAdmin(id);
        });
    }

}
