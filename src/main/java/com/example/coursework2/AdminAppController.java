package com.example.coursework2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AdminServer.Admins;
import AdminServer.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminAppController {

    @FXML
    private ComboBox<String> workerdepartament;

    @FXML
    private TextField workerfathername;

    @FXML
    private TextField workername;

    @FXML
    private TextField workerposition;

    @FXML
    private Spinner<Integer> workersalary;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(3,50,10);
    @FXML
    private TextField workersurname;

    @FXML
    private ComboBox<String> workeryear;

    @FXML
    private Button workerAddButton;

    @FXML
    private TextField ChouseIdForEdit;

    @FXML
    private Button EditButton;

    @FXML
    private TextField EditEmail;

    @FXML
    private TextArea EditLog;

    @FXML
    private TextField EditLogin;

    @FXML
    private TextField EditName;

    @FXML
    private TextField EditPassword;

    @FXML
    private TextField EditSurname;

    @FXML
    private Button FindIDButton;

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

    @FXML
    private TableView<Worker> ShowWorkerTable;

    @FXML
    private Button ShowWorkersButton;



    @FXML
    private Button FindWorker_Button;

    @FXML
    private TextField FindWorker_id;

    @FXML
    private TextField NameWorkerEdit;

    @FXML
    private TextField PositionWorkerEdit;

    @FXML
    private TextField FathernameWorkerEdit;

    @FXML
    private ComboBox<?> DepartamentWorkerEdit;

    @FXML
    private TextField SurnameWorkerEdit;

    @FXML
    private Button WorkerEditButton;

    @FXML
    private ComboBox<?> YearWorkerEdit;

    @FXML
    private Spinner<?> SalaryWorkerEdit;





    private ArrayList<Admins> AdminArrayList  = new ArrayList<>();
    private ArrayList<Worker> WorkerArrayList  = new ArrayList<>();


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

        FindWorker_Button.setOnAction(event->{
            String id = FindWorker_id.getText();
            AdminClient adminClient = new AdminClient();
            Worker worker;
            worker = adminClient.FindWorker(id);

            NameWorkerEdit.setText(worker.getWorker_name());
            SurnameWorkerEdit.setText(worker.getWorker_surname());
            FathernameWorkerEdit.setText(worker.getWorker_fathername());

        });

        FindIDButton.setOnAction(event->{
            String id = ChouseIdForEdit.getText();
            AdminClient adminClient = new AdminClient();
            Admins admin = new Admins();
            admin = adminClient.FindAdmin(id);

            EditLog.setText(admin.getAdmin_firstname()+" "+admin.getAdmin_lastname()+" "+admin.getAdmin_login()
            +" "+admin.getAdmin_password()+" "+admin.getAdmin_email());
            EditName.setText(admin.getAdmin_firstname());
            EditSurname.setText((admin.getAdmin_lastname()));
            EditLogin.setText(admin.getAdmin_login());
            EditPassword.setText(admin.getAdmin_password());
            EditEmail.setText(admin.getAdmin_email());
        });

        EditButton.setOnAction(event ->{
            String Name,Surname,Login,Password,Email,ID;
            Name = EditName.getText();
            Surname = EditSurname.getText();
            Login = EditLogin.getText();
            Password = EditPassword.getText();
            Email = EditEmail.getText();
            ID = ChouseIdForEdit.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.EditAdmin(ID,Name,Surname,Login,Password,Email);

        });

        ObservableList<String> departament = FXCollections.observableArrayList("Разработки","Продаж","Рекламмы","Логистики","Производственный","Кунилингуса","Минета");
        workerdepartament.setItems(departament);

        ObservableList<String> year = FXCollections.observableArrayList("1970","1971","1972","1973",
                "1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986",
                "1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999",
                "2000","2001","2002","2003");
        workeryear.setItems(year);

        workersalary.setValueFactory(svf);

        workerAddButton.setOnAction(event->{
            String Name = workername.getText();
            String Surname = workersurname.getText();
            String Fathername = workerfathername.getText();
            String Departament = workerdepartament.getSelectionModel().getSelectedItem().toString();
            String Position = workerposition.getText();
            String Year = workeryear.getSelectionModel().getSelectedItem().toString();
            String Salary = workersalary.getValue().toString();
            System.out.println("Зарплата = "+Salary);
            Worker worker = new Worker(Name,Surname,Fathername,Departament,Position,Year,Salary);
            AdminClient adminClient = new AdminClient();
            adminClient.AddWorker(Name,Surname,Fathername,Departament,Position,Salary,Year);
        });

        ShowWorkersButton.setOnAction(event->{
            AdminClient admin_client = new AdminClient();
            admin_client.showWorkers();
            ArrayList<Worker> workerArrayList = admin_client.showWorkers();
            this.WorkerArrayList = workerArrayList;
            ObservableList<Worker> observableList = FXCollections.observableArrayList(admin_client.showWorkers());
            ShowWorkerTable.setItems(observableList);
            ShowWorkerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            ShowWorkerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            ShowWorkerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_fathername"));
            ShowWorkerTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_departament"));
            ShowWorkerTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("Worker_position"));
            ShowWorkerTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("Worker_salary"));
            ShowWorkerTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("Worker_year"));
            ShowWorkerTable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("Worker_id"));
        });



    }

}
