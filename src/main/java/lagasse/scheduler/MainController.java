package lagasse.scheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

        @FXML
        private Button appointmentBtn;

        @FXML
        private Button customerBtn;

        @FXML
        private Button reportBtn;

        @FXML
        void onAppointmentBtn(ActionEvent event) throws IOException {
                Parent customerScene = FXMLLoader.load(getClass().getResource("/lagasse/scheduler/appointment-view.fxml"));
                Scene scene = new Scene(customerScene);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        }

        @FXML
        void onCustomerBtn(ActionEvent event) throws IOException {
                Parent customerScene = FXMLLoader.load(getClass().getResource("/lagasse/scheduler/customer-view.fxml"));
                Scene scene = new Scene(customerScene);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

        }

        @FXML
        void onReportBtn(ActionEvent event) throws IOException {
                Parent customerScene = FXMLLoader.load(getClass().getResource("/lagasse/scheduler/report-view.fxml"));
                Scene scene = new Scene(customerScene);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        }


    }