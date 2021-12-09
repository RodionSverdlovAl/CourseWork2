package com.example.coursework2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Clasess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class UserAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AccountingWorkers> ACCOUNTING_TABLE;

    @FXML
    private Button ANALITIC_RELOAD_BUTTON;

    @FXML
    private Button DELETE_WORKER_BUTTON;

    @FXML
    private PieChart DEPARTAMENT_DIAGRAM;

    @FXML
    private Text DEP_1;

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
    private Text DEP_4;

    @FXML
    private Text DEP_41;

    @FXML
    private Text DEP_5;

    @FXML
    private Text DEP_51;

    @FXML
    private TextArea FinishSalary;

    @FXML
    private PieChart HOUR_PIECHART;

    @FXML
    private Button HOUR_PIECHART_BUTTON;

    @FXML
    private TextField ID_FOR_DELETE_WORKER;

    @FXML
    private PieChart REBUKE_PIECHART;

    @FXML
    private Button RELOAD_ACCOUNTING;

    @FXML
    private Button RELOAD_PIECHART_REBOK;

    @FXML
    private TableView<Salary> SalaryTable;

    @FXML
    private Button ShowSalaryButton;

    @FXML
    private TableView<Worker> ShowWorkerTable;

    @FXML
    private Button ShowWorkersButton;

    private ArrayList<Worker> WorkerArrayList  = new ArrayList<>();
    private ArrayList<AccountingWorkers> AccountingWorkerArrayList  = new ArrayList<>();
    private ArrayList<Salary> salaryWorkers = new ArrayList<>();

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
