package lagasse.scheduler.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lagasse.scheduler.dao.*;
import lagasse.scheduler.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentView implements Initializable {
    @FXML
    private TableColumn<?, ?> appIdCol;

    @FXML
    private TableColumn<?, ?> appContactCol;

    @FXML
    private TableColumn<?, ?> appCustomerCol;

    @FXML
    private TableColumn<?, ?> appDescriptionCol;

    @FXML
    private TableColumn<?, ?> appEndCol;

    @FXML
    private TableColumn<?, ?> appLocationCol;

    @FXML
    private TableColumn<?, ?> appStartCol;

    @FXML
    private TableColumn<?, ?> appTitleCol;

    @FXML
    private TableColumn<?, ?> appTypeCol;

    @FXML
    private TableColumn<?, ?> appUserCol;

    @FXML
    private TableView<?> appointmentTable;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> transferCustomer = FXCollections.observableArrayList();
        ObservableList<User>transferUser = FXCollections.observableArrayList();
        ObservableList<Contact>transferContact = FXCollections.observableArrayList();

        try {
            transferCustomer.setAll(CustomerDAO.getAll());
            transferUser.setAll(UserDAO.getAll());
            transferContact.setAll(ContactDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appUserCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));


        stateCombo.setItems(transferDivisions);
        customerTableView.setItems(transferCustomer);

        countryCombo.setItems(transferCountry);
    }
}
