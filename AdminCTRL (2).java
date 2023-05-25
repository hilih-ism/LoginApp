package com.example.loginapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.*;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminCTRL  {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/LoginApp";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static final String input = "INSERT INTO employee (`EmpName`,`EmpEmail`,`EmpUser`,`EmpPass`) VALUES (?, ? ,?, ?)";

    @FXML
    private Button addEmp;

    @FXML
    private Button addItem;

    @FXML
    private TextField empEmail;

    @FXML
    private TextField empName;

    @FXML
    private TextField empPass;

    @FXML
    private TextField empUser;

    @FXML
    private TextField itemID;

    @FXML
    private TextField itemname;

    @FXML
    private TextField itemprice;
    @FXML
    private Button admLogout;
    private Alert alert;

    public static void insertData(Scene scene) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        TextField Fnametxt = (TextField) scene.lookup("#empName");
        String inputVal = Fnametxt.getText();
        TextField emailtxt = (TextField) scene.lookup("#empEmail");
        String inputVal2 = emailtxt.getText();
        TextField usertxt = (TextField) scene.lookup("#empUser");
        String inputVal3 = usertxt.getText();
        TextField passtxt = (TextField) scene.lookup("#empPass");
        String inputVal4 = passtxt.getText();

        PreparedStatement statement = con.prepareStatement(input);
        statement.setString(1, inputVal);
        statement.setString(2, inputVal2);
        statement.setString(3, inputVal3);
        statement.setString(4, inputVal4);
        statement.executeUpdate();

        con.close();
    }

    public void setAddEmp(ActionEvent event) {
        Button addEmployee = (Button) event.getSource();
        Scene scene = addEmployee.getScene();

        try {
            insertData(scene);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Insertion");
            alert.setHeaderText(null);
            alert.setContentText("Successfully entered employee into database");
            alert.showAndWait();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertItem(Scene scene) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        String query = "INSERT INTO items (`ItemName`,`ItemID`,`ItemPrice`) VALUES (?, ? ,?)";
        TextField name = (TextField) scene.lookup("#itemname");
        String inputVal = name.getText();
        TextField ID = (TextField) scene.lookup("#itemID");
        String inputVal2 = ID.getText();
        TextField price = (TextField) scene.lookup("#itemprice");
        String inputVal3 = price.getText();

        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, inputVal);
        statement.setString(2, inputVal2);
        statement.setString(3, inputVal3);
        statement.executeUpdate();

        con.close();
    }

    public void setAddItem(ActionEvent event) {
        Button addItem = (Button) event.getSource();
        Scene scene = addItem.getScene();

        try {
            insertItem(scene);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item Insertion");
            alert.setHeaderText(null);
            alert.setContentText("Successfully entered item into database");
            alert.showAndWait();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void adminLogout(ActionEvent event){
        if(event.getSource().equals(admLogout)){
            try {
                HelloApplication.sceneFactory("/com/example/loginapp/hello-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
