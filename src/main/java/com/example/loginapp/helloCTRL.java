package com.example.loginapp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    private Alert alert;

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnlogin)) {
            if (usertxt.getText().isEmpty() || passtxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username/Password field cannot be empty");
                alert.showAndWait();
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

