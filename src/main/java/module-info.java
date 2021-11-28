module com.example.coursework2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coursework2 to javafx.fxml;
    exports com.example.coursework2;
}