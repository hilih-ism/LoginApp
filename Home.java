package com.example.loginapp;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private Button logoutbtn;

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource().equals(logoutbtn)){
            try {
                HelloApplication.sceneFactory("/com/example/loginapp/hello-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       logoutbtn.setOnAction(this);
    }
}
