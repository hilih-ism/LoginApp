package com.example.loginapp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;
import java.sql.*;
public class HelloApplication extends Application {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/LoginApp";
    static final String DB_USER = "root";
    static final String DB_PASS = "";

    public static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
//
        scene = new Scene(loadFXML("/com/example/loginapp/hello-view"));
        stage.sizeToScene();
        stage.setTitle("Loop Loom");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image image = new Image("C:\\Users\\Yemisrach\\Desktop\\Uni\\3rd yr\\2nd sem\\AP\\LoginApp\\src\\main\\resources\\com\\example\\loginapp\\logo2.PNG");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws Exception {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        launch();
    }
    public static void sceneFactory(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().sizeToScene();

    }
    private static Parent loadFXML(String fxml) throws IOException {

        return FXMLLoader.load(HelloApplication.class.getResource(fxml+".fxml"));
    }
}