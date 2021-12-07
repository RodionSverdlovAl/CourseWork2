package com.example.coursework2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Clasess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AdminAppController {

    @FXML
    private Button RELOAD_ACCOUNTING;
    @FXML
    private TableView<AccountingWorkers> ACCOUNTING_TABLE;
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
    private Button Accounting_add_worker1;
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
    @FXML
    private TableView<Users> SHOWUSERTABLE;
    @FXML
    private TextField UserDeleteID;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button showUsersButton;
    @FXML
    private TableView<Salary> SalaryTable;
    @FXML
    private Button ShowSalaryButton;
    @FXML
    private TextArea FinishSalary;
    @FXML
    private Button ANALITIC_RELOAD_BUTTON;


    @FXML
    private PieChart HOUR_PIECHART;
    @FXML
    private Button HOUR_PIECHART_BUTTON;
    @FXML
    private Text DEP_313;
    @FXML
    private Text DEP_32;
    @FXML
    private Text DEP_33;
    @FXML
    private Text DEP_34;
    @FXML
    private Text DEP_35;


    @FXML
    private Text DEP_11;
    @FXML
    private Text DEP_2;
    @FXML
    private Text DEP_21;
    @FXML
    private Text DEP_3;
    @FXML
    private Text DEP_31;
    @FXML
    private Text DEP_4;
    @FXML
    private Text DEP_41;
    @FXML
    private Text DEP_5;
    @FXML
    private Text DEP_51;
    @FXML
    private Text DEP_1;
    @FXML
    private Button RELOAD_PIECHART_REBOK;
    @FXML
    private PieChart REBUKE_PIECHART;

    private ArrayList<Admins> AdminArrayList  = new ArrayList<>();
    private ArrayList<Users> UserArrayList  = new ArrayList<>();
    private ArrayList<Worker> WorkerArrayList  = new ArrayList<>();
    private ArrayList<AccountingWorkers> AccountingWorkerArrayList  = new ArrayList<>();
    private ArrayList<Salary> salaryWorkers = new ArrayList<>();

    @FXML
    private PieChart DEPARTAMENT_DIAGRAM;

    @FXML
    void initialize() {

        RELOAD_PIECHART_REBOK.setOnAction(event->{
            AdminClient adminClient2 = new AdminClient();
            ArrayList<AccountingWorkers> Workers2 = adminClient2.showAccountingWorkers();
            int dev,sell,add,log,man;
            dev=sell=add=log=man = 0;
            for(AccountingWorkers p : Workers2){
                if(p.getWorker_departament().length()==10 && p.getAcc_rebuke().length()==4){
                    dev++;
                }
                if(p.getWorker_departament().length() == 6&& p.getAcc_rebuke().length()==4){
                    sell++;
                }
                if(p.getWorker_departament().length()==8&& p.getAcc_rebuke().length()==4){
                    add++;
                }
                if(p.getWorker_departament().length()==9&& p.getAcc_rebuke().length()==4){
                    log++;
                }
                if(p.getWorker_departament().length()==16&& p.getAcc_rebuke().length()==4){
                    man++;
                }
            }
            DEP_11.setText("Отдел разработки: "+dev + " выговоров");
            DEP_21.setText("Отдел Продаж: "+sell+" выговоров");
            DEP_31.setText("Отдел Рекламмы: "+add+" выговоров");
            DEP_41.setText("Отдел Логистики: "+log+" выговоров");
            DEP_51.setText("Отдел Производственный: "+man+" выговоров");

            ObservableList<PieChart.Data> piechartdata =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Разработки",dev),
                            new PieChart.Data("Продаж",sell),
                            new PieChart.Data("Рекламмы",add),
                            new PieChart.Data("Логистики",log),
                            new PieChart.Data("Производственный",man));
            REBUKE_PIECHART.getData().clear();
            REBUKE_PIECHART.getData().addAll(piechartdata);
        });


        HOUR_PIECHART_BUTTON.setOnAction(event->{
            AdminClient adminClient2 = new AdminClient();
            ArrayList<AccountingWorkers> Workers2 = adminClient2.showAccountingWorkers();
            int dev,sell,add,log,man;
            dev=sell=add=log=man = 0;
            for(AccountingWorkers p : Workers2){
                if(p.getWorker_departament().length()==10){
                    dev+=Integer.parseInt(p.getAcc_hour());
                }
                if(p.getWorker_departament().length() == 6){
                    sell+=Integer.parseInt(p.getAcc_hour());
                }
                if(p.getWorker_departament().length()==8){
                    add+=Integer.parseInt(p.getAcc_hour());
                }
                if(p.getWorker_departament().length()==9){
                    log+=Integer.parseInt(p.getAcc_hour());
                }
                if(p.getWorker_departament().length()==16){
                    man+=Integer.parseInt(p.getAcc_hour());
                }
            }
            DEP_313.setText("Отдел разработки: "+dev + " часов");
            DEP_32.setText("Отдел Продаж: "+sell+" часов");
            DEP_33.setText("Отдел Рекламмы: "+add+" часов");
            DEP_34.setText("Отдел Логистики: "+log+" часов");
            DEP_35.setText("Отдел Производственный: "+man+" часов");

            ObservableList<PieChart.Data> piechartdata =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Разработки",dev),
                            new PieChart.Data("Продаж",sell),
                            new PieChart.Data("Рекламмы",add),
                            new PieChart.Data("Логистики",log),
                            new PieChart.Data("Производственный",man));
            HOUR_PIECHART.getData().clear();
            HOUR_PIECHART.getData().addAll(piechartdata);
        });

        ANALITIC_RELOAD_BUTTON.setOnAction(event->{
            AdminClient adminClient2 = new AdminClient();
            ArrayList<Worker> Workers2 = adminClient2.showWorkers();
            int dev,sell,add,log,man;
            dev=sell=add=log=man = 0;
            for(Worker p : Workers2){
                if(p.getWorker_departament().length()==10){
                    dev++;
                }
                if(p.getWorker_departament().length() == 6){
                    sell++;
                }
                if(p.getWorker_departament().length()==8){
                    add++;
                }
                if(p.getWorker_departament().length()==9){
                    log++;
                }
                if(p.getWorker_departament().length()==16){
                    man++;
                }
            }
            DEP_1.setText("Отдел разработки: "+dev);
            DEP_2.setText("Отдел Продаж: "+sell);
            DEP_3.setText("Отдел Рекламмы: "+add);
            DEP_4.setText("Отдел Логистики: "+log);
            DEP_5.setText("Отдел Производственный: "+man);

            ObservableList<PieChart.Data> piechartdata =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Разработки",dev),
                            new PieChart.Data("Продаж",sell),
                            new PieChart.Data("Рекламмы",add),
                            new PieChart.Data("Логистики",log),
                            new PieChart.Data("Производственный",man));
            DEPARTAMENT_DIAGRAM.getData().clear();
            DEPARTAMENT_DIAGRAM.getData().addAll(piechartdata);


        });

        ShowSalaryButton.setOnAction(event->{
            AdminClient adminClient = new AdminClient();
            adminClient.ShowSalaryWorkers();
            ArrayList<Salary> salaryWorkers = adminClient.ShowSalaryWorkers();
            this.salaryWorkers = salaryWorkers;
            ObservableList<Salary> observableList  =FXCollections.observableArrayList(adminClient.ShowSalaryWorkers());
            SalaryTable.setItems(observableList);
            SalaryTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_id"));
            SalaryTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            SalaryTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            SalaryTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_departament"));
            SalaryTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("Worker_salary"));

            double SumSalary = 0.0;
            for(Salary p : salaryWorkers){
                SumSalary += Double.valueOf(p.getWorker_salary());
            }
            FinishSalary.setText("Итого: "+String.valueOf(SumSalary)+" рублей");

        });


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

        deleteUserButton.setOnAction(event->{
            String id = UserDeleteID.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.DeleteUser(id);
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

        RELOAD_ACCOUNTING.setOnAction(event->{
            AdminClient adminClient = new AdminClient();
            adminClient.showAccountingWorkers();
            ArrayList<AccountingWorkers> accountingWorkers = adminClient.showAccountingWorkers();
            this.AccountingWorkerArrayList = accountingWorkers;
            ObservableList<AccountingWorkers> observableList  =FXCollections.observableArrayList(adminClient.showAccountingWorkers());
            ACCOUNTING_TABLE.setItems(observableList);
            ACCOUNTING_TABLE.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            ACCOUNTING_TABLE.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            ACCOUNTING_TABLE.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_departament"));
            ACCOUNTING_TABLE.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_position"));
            ACCOUNTING_TABLE.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("Acc_hour"));
            ACCOUNTING_TABLE.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("Acc_bonus"));
            ACCOUNTING_TABLE.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("Acc_rebuke"));
        });

        showUsersButton.setOnAction(event->{
            AdminClient admin_client = new AdminClient();
            admin_client.showUsers();
            ArrayList<Users> userArrayList = admin_client.showUsers();
            this.UserArrayList =  userArrayList;
            ObservableList<Users> observableList = FXCollections.observableArrayList(admin_client.showUsers());
            SHOWUSERTABLE.setItems(observableList);
            SHOWUSERTABLE.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("user_id"));
            SHOWUSERTABLE.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("user_firstname"));
            SHOWUSERTABLE.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("user_lastname"));
            SHOWUSERTABLE.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("user_login"));
            SHOWUSERTABLE.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("user_password"));
            SHOWUSERTABLE.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("user_email"));
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

        Accounting_add_worker1.setOnAction(event->{ // удаление работника из учета
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.DeleteAccountingWorker(id);
        });

        Accounting_add_worker.setOnAction(event->{
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            Worker worker;
            worker = adminClient.FindWorker(id);
            adminClient.Accounting_add_worker(worker);
        });
        Accounting_add_rebuke_button.setOnAction(event->{
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.Accounting_add_rebuke(id);
        });
        Accounting_delete_rebuke_button.setOnAction(event->{
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.Accounting_delete_rebuke(id);
        });
        Accounting_bonus_button.setOnAction(event->{
            Integer bonus_p = Accounting_select_procent.getValue();
            String id = Accounting_id.getText();
            AdminClient adminClient = new AdminClient();
            adminClient.Accounting_bonus(bonus_p,id);
        });
        Accounting_add_hour_button.setOnAction(event->{
            String id = Accounting_id.getText();
            Integer hours = Accounting_select_hour.getValue();
            AdminClient adminClient = new AdminClient();
            adminClient.Accounting_add_hour(hours,id);
        });
    }
}