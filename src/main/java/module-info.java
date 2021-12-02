module com.example.coursework2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.coursework2 to javafx.fxml;
    exports com.example.coursework2;
    exports AdminServer;



}