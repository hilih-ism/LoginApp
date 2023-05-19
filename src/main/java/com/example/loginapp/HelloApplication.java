package com.example.loginapp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Loop Loom");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image image = new Image("C:\\Users\\Yemisrach\\Desktop\\Uni\\3rd yr\\2nd sem\\AP\\LoginApp\\src\\main\\resources\\com\\example\\loginapp\\icon.PNG");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}