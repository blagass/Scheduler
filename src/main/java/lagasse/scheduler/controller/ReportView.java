package lagasse.scheduler.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportView {

    @FXML
    private Button exitBtn;

    @FXML
    void onExitBtn(ActionEvent event) throws IOException {
        Parent customerScene = FXMLLoader.load(getClass().getResource("/lagasse/scheduler/main-view.fxml"));
        Scene scene = new Scene(customerScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
