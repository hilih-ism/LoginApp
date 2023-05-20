package com.example.loginapp;

        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.stage.Stage;
        import javafx.scene.control.Hyperlink;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class helloCTRL implements Initializable, EventHandler<ActionEvent> {
private Stage stage;
    @FXML
    private Button btnlogin;
    @FXML
    private Hyperlink reglink;

    @Override
    public void handle(ActionEvent actionEvent)  {
        if(actionEvent.getSource().equals(btnlogin)){
            try {
                HelloApplication.sceneFactory("/com/example/loginapp/Home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource().equals(reglink)){
            try {
                HelloApplication.sceneFactory("/com/example/loginapp/Register");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     btnlogin.setOnAction(this);
     reglink.setOnAction(this);
    }
}
