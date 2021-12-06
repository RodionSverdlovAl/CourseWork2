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
    private Button Accounting_add_worker;
    @FXML
    private TableView<Worker> Accounting_table;
    @FXML
    private TextField Accounting_id;
    @FXML
    private Button Accounting_add_hour_button;
    @FXML
    private Button Accounting_add_rebuke_button;
    @FXML
    private Button Accounting_bonus_button;
    @FXML
    private Button Accounting_delete_rebuke_button;
    @FXML
    private TextArea Accounting_logs_area;
    @FXML
    private Button Accounting_select_button;
    @FXML
    private Spinner<Integer> Accounting_select_hour;
    SpinnerValueFactory<Integer> hour = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,1);
    @FXML
    private ComboBox<Integer> Accounting_select_procent;
    @FXML
    private TextArea Accounting_worker_info;
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
    private ComboBox<String> DepartamentWorkerEdit;
    @FXML
    private TextField SurnameWorkerEdit;
    @FXML
    private Button WorkerEditButton;
    @FXML
    private ComboBox<String> YearWorkerEdit;
    @FXML
    private Button DELETE_WORKER_BUTTON;
    @FXML
    private TextField ID_FOR_DELETE_WORKER;
    @FXML
    private Spinner<Integer> SalaryWorkerEdit;

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

        DELETE_WORKER_BUTTON.setOnAction(event->{
            String id  = ID_FOR_DELETE_WORKER.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.DeleteWorker(id);
        });

        DeleteAdminButton.setOnAction(event ->{
            String id = idForDelete.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.DeleteAdmin(id);
        });

        ObservableList<String> departament = FXCollections.observableArrayList("Разработки","Продаж","Рекламмы","Логистики","Производственный");
        workerdepartament.setItems(departament);
        DepartamentWorkerEdit.setItems(departament);

        ObservableList<String> year = FXCollections.observableArrayList("1970","1971","1972","1973",
                "1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986",
                "1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999",
                "2000","2001","2002","2003");
        workeryear.setItems(year);
        YearWorkerEdit.setItems(year);

        ObservableList<Integer> bonus = FXCollections.observableArrayList(10,15,20,25,30,35,40,50);
        Accounting_select_procent.setItems(bonus);


        workersalary.setValueFactory(svf);
        SalaryWorkerEdit.setValueFactory(svf);
        Accounting_select_hour.setValueFactory(hour);

        FindWorker_Button.setOnAction(event->{
            String id = FindWorker_id.getText();
            AdminClient adminClient = new AdminClient();
            Worker worker;
            worker = adminClient.FindWorker(id);

            NameWorkerEdit.setText(worker.getWorker_name());
            SurnameWorkerEdit.setText(worker.getWorker_surname());
            FathernameWorkerEdit.setText(worker.getWorker_fathername());
            PositionWorkerEdit.setText(worker.getWorker_position());

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

        WorkerEditButton.setOnAction(event->{
            String Name,Surname,Fathername,Departament,Position,Salary,Year;
            Name = NameWorkerEdit.getText();
            Surname = SurnameWorkerEdit.getText();
            Fathername = FathernameWorkerEdit.getText();
            Departament = DepartamentWorkerEdit.getSelectionModel().getSelectedItem();
            Position = PositionWorkerEdit.getText();
            Year = YearWorkerEdit.getSelectionModel().getSelectedItem();
            Salary = SalaryWorkerEdit.getValue().toString();
            String ID = FindWorker_id.getText();
            System.out.println(ID+Name + Surname +Fathername+Departament+Position+Year+Salary);
            AdminClient adminClient = new AdminClient();
            adminClient.EditWorker(ID,Name,Surname,Fathername,Departament,Position,Salary,Year);
        });

        workerAddButton.setOnAction(event->{
            String Name = workername.getText();
            String Surname = workersurname.getText();
            String Fathername = workerfathername.getText();
            String Departament = workerdepartament.getSelectionModel().getSelectedItem();
            String Position = workerposition.getText();
            String Year = workeryear.getSelectionModel().getSelectedItem();
            String Salary = workersalary.getValue().toString();
            System.out.println("Зарплата = "+Salary);
            Worker worker = new Worker(Name,Surname,Fathername,Departament,Position,Year,Salary);
            AdminClient adminClient = new AdminClient();
            adminClient.AddWorker(Name,Surname,Fathername,Departament,Position,Salary,Year);
            workername.clear();
            workersurname.clear();
            workerfathername.clear();
            workerposition.clear();
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

        Accounting_select_button.setOnAction(event->{
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            Worker worker;
            worker = adminClient.FindWorker(id);

            ArrayList<Worker> workerArrayList = new ArrayList<>();
            workerArrayList.add(worker);
            this.WorkerArrayList = workerArrayList;
            ObservableList<Worker> observableList = FXCollections.observableArrayList(WorkerArrayList);
            Accounting_table.setItems(observableList);
            Accounting_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            Accounting_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            Accounting_table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_departament"));
            Accounting_table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_position"));

        });

        Accounting_add_worker.setOnAction(event->{
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            Worker worker;
            worker = adminClient.FindWorker(id);
            adminClient.Accounting_add_worker(worker);
        });

    }
}
