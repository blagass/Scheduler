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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lagasse.scheduler.dao.CountryDAO;
import lagasse.scheduler.dao.CustomerDAO;
import lagasse.scheduler.dao.FirstLevelDivisionDAO;
import lagasse.scheduler.model.Country;
import lagasse.scheduler.model.Customer;
import lagasse.scheduler.model.FirstLevelDivision;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerView implements Initializable {

    public TextField customerNameField;
    public TextField customerAddressField;
    public TextField customerPostalCodeField;
    public TextField customerPHoneField;
    @FXML
    private TableColumn<?, ?> custAddressCol;

    @FXML
    private TableColumn<?, ?> custCountryCol;

    @FXML
    private TableColumn<?, ?> custIdCol;

    @FXML
    private TableColumn<?, ?> custNameCol;

    @FXML
    private TableColumn<?, ?> custPhoneCol;

    @FXML
    private TableColumn<?, ?> custPostalCol;

    @FXML
    private TableColumn<?, ?> custStateCol;

    @FXML
    private ComboBox<Country> countryCombo;

    @FXML
    private ComboBox<FirstLevelDivision> stateCombo;


    @FXML
    private TableView<Customer> customerTableView;



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
        //Transfer Lists
        ObservableList<Customer>transferCustomer = FXCollections.observableArrayList();
        ObservableList<Country>transferCountry = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivision>transferDivisions = FXCollections.observableArrayList();

        //Country/State
        ObservableList<Country>usDivisions = FXCollections.observableArrayList();
        ObservableList<Country>canadaDivisions = FXCollections.observableArrayList();
        ObservableList<Country>ukDivisions = FXCollections.observableArrayList();


        try {
             transferCustomer.setAll(CustomerDAO.getAll());
             transferCountry.setAll(CountryDAO.getAll());
             transferDivisions.setAll(FirstLevelDivisionDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custCountryCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        stateCombo.setItems(transferDivisions);
        customerTableView.setItems(transferCustomer);
        countryCombo.setItems(transferCountry);

    }

    @FXML
    void onCountryCombo(ActionEvent event) {



    }

    @FXML
    void onStateCombo(ActionEvent event) {

    }



    @FXML
    public void countryComboRelease(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
