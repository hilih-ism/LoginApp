package com.example.loginapp;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.sql.*;

public class Register {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/LoginApp";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static final String input = "INSERT INTO customer (Full Name) VALUES (?)";
    public static void main(String[] args) throws Exception {
       insertData(HelloApplication.scene);
    }
    public static void insertData(Scene scene, ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);

        TextField Fnametxt = (TextField) scene.lookup("#Fname");
        String inputVal = Fnametxt.getText();

        PreparedStatement statement = con.prepareStatement(input);
        statement.setString(1, inputVal);
        statement.executeUpdate();

    }
}