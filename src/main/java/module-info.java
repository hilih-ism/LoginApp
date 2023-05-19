module com.example.loginapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.loginapp to javafx.fxml;
    exports com.example.loginapp;
}