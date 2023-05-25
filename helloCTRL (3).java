package com.example.loginapp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.io.File;

import static com.example.loginapp.HelloApplication.scene;

public class helloCTRL implements Initializable, EventHandler<ActionEvent> {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/LoginApp";
    static final String DB_USER = "root";
    static final String DB_PASS = "";

    @FXML
    private Button btnlogin;
    @FXML
    private TextField usertxt;
    @FXML
    private TextField passtxt;
    @FXML
    private Hyperlink reglink;

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnlogin)) {
            Alert alert;
            if (usertxt.getText().isEmpty() || passtxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username/Password field cannot be empty");
                alert.showAndWait();
            } else if (usertxt.getText().equals("admin") && passtxt.getText().equals("admin")) {
                try {
                    HelloApplication.sceneFactory("/com/example/loginapp/Admin");
                    scene.getWindow().sizeToScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                String username = usertxt.getText();
                String password = passtxt.getText();

                try {
                    Class.forName(DB_DRIVER);
                    Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

                    String query = "SELECT * FROM customer WHERE Username = ? AND Password = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        // Successful login
                        try {
                            HelloApplication.sceneFactory("/com/example/loginapp/Home");
                            LocalDateTime loginTime = LocalDateTime.now();

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String loginTimeStr = loginTime.format(formatter);
                            try {
                                String query2 = "INSERT INTO logins (`LoginTime`,`User`) VALUES (?,?)";
                                PreparedStatement statement = con.prepareStatement(query2);
                                statement.setString(1, loginTimeStr);
                                statement.setString(2, username);
                                statement.executeUpdate();
                                con.close();

                                String filePath = "C:/Users/Yemisrach/Desktop/Uni/3rd yr/2nd sem/AP/logindata.txt";
                                File file = new File(filePath);
                                file.createNewFile();
                                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                                writer.write(loginTimeStr);
                                writer.newLine();
                                writer.write(username);
                                writer.newLine();
                                writer.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Invalid login credentials
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid login credentials");
                        alert.showAndWait();
                    }

                    rs.close();
                    ps.close();
                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (actionEvent.getSource().equals(reglink)) {
            try {
                HelloApplication.sceneFactory("/com/example/loginapp/Register");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnlogin.setOnAction(this);
        reglink.setOnAction(this);
    }

}

