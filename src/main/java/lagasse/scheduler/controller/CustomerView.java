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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerView implements Initializable {
    @FXML
    private Button exitBtn;

    @FXML
    private Button saveUpdateButton;

    @FXML
    private boolean editMode;


    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label postalLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label addCustomerLabel;

    @FXML
    private Label edictCustomerLabel;

    @FXML
    private Button cancelButton;


    @FXML
    private Button saveNewCustomer;

    //text fields
    public TextField customerNameField;
    public TextField customerAddressField;
    public TextField customerPostalCodeField;
    public TextField customerPHoneField;


    //table variables
    @FXML
    private TableView<Customer> customerTableView;
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


    //transfer customer variables
    @FXML
    public Customer currentlySelectedCustomer;
    @FXML
    public Customer transferCustomer;

    //combo boxes
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<FirstLevelDivision> stateCombo;
    @FXML

    //Observable lists
    private ObservableList<Customer> allCustomers;
    private ObservableList<Country> allCountries;
    private ObservableList<FirstLevelDivision> allDivisions;
    private ObservableList<FirstLevelDivision> usDivisions;
    private ObservableList<FirstLevelDivision> canadaDivisions;
    private ObservableList<FirstLevelDivision> ukDivisions;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setCustomers();
            setCountries();
            setDivisions();
            setTableColumns();
            setCombos();
            setVisible(false); //Trigger to hide top options

    }

    private void setCombos() {
        stateCombo.setItems(allDivisions);
        //stateCombo.getSelectionModel().selectFirst();
        countryCombo.setItems(allCountries);
        //countryCombo.getSelectionModel().selectFirst();
        customerTableView.setItems(allCustomers);
    }

    private void setDivisions() {

        try {
            allDivisions = FirstLevelDivisionDAO.getAll();
            usDivisions = FirstLevelDivisionDAO.usStates();
            canadaDivisions = FirstLevelDivisionDAO.canadaStates();
            ukDivisions = FirstLevelDivisionDAO.ukStates();
        } catch (SQLException e){
            System.out.println("Error");
        }

    }

    private void setCountries() {
        try {
            allCountries = CountryDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCustomers() {
        try {
            allCustomers = CustomerDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setVisible(boolean visible){
        customerNameField.setVisible(visible);
        customerAddressField.setVisible(visible);
        customerPostalCodeField.setVisible(visible);
        customerPHoneField.setVisible(visible);
        countryCombo.setVisible(visible);
        stateCombo.setVisible(visible);
        nameLabel.setVisible(visible);
        addressLabel.setVisible(visible);
        postalLabel.setVisible(visible);
        phoneLabel.setVisible(visible);
        countryLabel.setVisible(visible);
        stateLabel.setVisible(visible);
        edictCustomerLabel.setVisible(visible);
        addCustomerLabel.setVisible(visible);
        saveUpdateButton.setVisible(visible);
        cancelButton.setVisible(visible);


    }


    @FXML
    private void setTableColumns(){
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custCountryCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        custStateCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
    }

    @FXML
    void onEditCustomer(ActionEvent event) throws SQLException {
        editMode = true;
        setVisible(true);
        customerTableView.setDisable(true);
        saveUpdateButton.setVisible(true);
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        int divisionId = selectedCustomer.getDivisionId();
        if (selectedCustomer != null) {
            currentlySelectedCustomer = selectedCustomer;
            populateFormFields(selectedCustomer);
            loadStatesForSelectedCountry(CustomerDAO.getCountryId(divisionId));
        }

    }


    private void populateFormFields(Customer selectedCustomer) {
        customerNameField.setText(selectedCustomer.getCustomerName());
        customerAddressField.setText(selectedCustomer.getAddress());
        customerPostalCodeField.setText(selectedCustomer.getPostalCode());
        customerPHoneField.setText(selectedCustomer.getPhone());
    }

    private void loadStatesForSelectedCountry(int countryId) {

        Country country = null;
        try {
            country = CountryDAO.getCountry(countryId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        countryCombo.setValue(country);

        Country selectedCountry = countryCombo.getSelectionModel().getSelectedItem();
        if (selectedCountry != null) {
            if (countryCombo.getSelectionModel().getSelectedItem().getCountryId() == 1) {
                stateCombo.setItems(usDivisions);
            } else if (countryCombo.getSelectionModel().getSelectedItem().getCountryId() == 2) {
                stateCombo.setItems(canadaDivisions);
            } else if (countryCombo.getSelectionModel().getSelectedItem().getCountryId() == 3) {
                stateCombo.setItems(ukDivisions);
            }
        }


        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        int divisionId = selectedCustomer.getDivisionId();
        FirstLevelDivision fld = null;
        try {
            fld = FirstLevelDivisionDAO.getFld(divisionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        stateCombo.setValue(fld);
    }




    @FXML
    void onCountryCombo(ActionEvent event) throws SQLException {
if(!editMode) {
//    Country countryComboSelect = countryCombo.getSelectionModel().getSelectedItem();
//    if (countryComboSelect.getCountryId() == 1) {
//        stateCombo.setItems(usDivisions);
//
//
//    } else if (countryComboSelect.getCountryId() == 2) {
//        stateCombo.setItems(ukDivisions);
//
//    } else if (countryComboSelect.getCountryId() == 3) {
//        stateCombo.setItems(canadaDivisions);
//
//    } else {
//        System.out.println("Select a Country");
//    }
}else {

    Country countryComboSelect = countryCombo.getSelectionModel().getSelectedItem();
    if (countryComboSelect.getCountryId() == 1) {
        stateCombo.setItems(usDivisions);
        //stateCombo.getSelectionModel().selectFirst();

    } else if (countryComboSelect.getCountryId() == 2) {
        stateCombo.setItems(ukDivisions);
       // stateCombo.getSelectionModel().selectFirst();
    } else if (countryComboSelect.getCountryId() == 3) {
        stateCombo.setItems(canadaDivisions);
      //  stateCombo.getSelectionModel().selectFirst();
    } else {
        System.out.println("Select a Country");
    }
}
}
public void clearFields(){
        editMode = false;
    setVisible(false);
    customerTableView.setDisable(false);
    customerNameField.clear();
    customerAddressField.clear();
    customerPostalCodeField.clear();
    customerPHoneField.clear();
    countryCombo.getSelectionModel().clearSelection();
    stateCombo.getSelectionModel().clearSelection();
}

    public void onCancelButton(ActionEvent actionEvent) {
    clearFields();
    saveNewCustomer.setVisible(false);
    }
    @FXML
    public void countryComboRelease() throws SQLException {
//
//        //Country/State
//
//        ObservableList<FirstLevelDivision> usDivisions = FXCollections.observableArrayList();
//        ObservableList<FirstLevelDivision> canadaDivisions = FXCollections.observableArrayList();
//        ObservableList<FirstLevelDivision> ukDivisions = FXCollections.observableArrayList();
//
//        usDivisions.setAll(FirstLevelDivisionDAO.usStates());
//        canadaDivisions.setAll(FirstLevelDivisionDAO.canadaStates());
//        ukDivisions.setAll(FirstLevelDivisionDAO.ukStates());
//
//
//        Country countryComboSelect = countryCombo.getSelectionModel().getSelectedItem();
//
//        if (countryComboSelect.getCountryId() == 1) {
//            stateCombo.setItems(usDivisions);
//            stateCombo.getSelectionModel().selectFirst();
//        } else if (countryComboSelect.getCountryId() == 2) {
//            stateCombo.setItems(canadaDivisions);
//            stateCombo.getSelectionModel().selectFirst();
//        } else if (countryComboSelect.getCountryId() == 3) {
//            stateCombo.setItems(ukDivisions);
//            stateCombo.getSelectionModel().selectFirst();
//        } else {
//            System.out.println("Select a Country");
//        }
    }

    @FXML
    void onSaveNewCustomer(ActionEvent event) throws SQLException {
        Customer customer = new Customer(-0,"test","101 street","1234","22354", 1,"canadas");


        String customerName = customerNameField.getText();
        String customerAddress = customerAddressField.getText();
        String customerPostal = customerPostalCodeField.getText();
        String customerPhone = customerPHoneField.getText();
        int customerDivisionId  = countryCombo.getSelectionModel().getSelectedItem().getCountryId();
        String customerDivisionName = stateCombo.getSelectionModel().getSelectedItem().toString();
        System.out.println(customerName);


        customer.setCustomerName(customerName);
        customer.setAddress(customerAddress);
        customer.setPostalCode(customerPostal);
        customer.setPhone(customerPhone);
        customer.setDivisionId(customerDivisionId);
        customer.setDivisionName(customerDivisionName);

        transferCustomer  = customer;
        CustomerDAO customerDao = new CustomerDAO();
        System.out.println("Number of customers in the database: " + CustomerDAO.getAll().size());
        customerDao.add(customer);


        //Transfer Lists
        ObservableList<Customer>transferCustomer = FXCollections.observableArrayList();
        ObservableList<Country>transferCountry = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivision>transferDivisions = FXCollections.observableArrayList();

        try {
            transferCustomer.setAll(CustomerDAO.getAll());
            transferCountry.setAll(CountryDAO.getAll());
            transferDivisions.setAll(FirstLevelDivisionDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerTableView.setItems(transferCustomer);
        setVisible(false);
        customerTableView.setDisable(false);
        clearFields();
        saveNewCustomer.setVisible(false);
    }

    @FXML
    void onDeleteCustomer(ActionEvent event) throws SQLException {
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        CustomerDAO customerDao = new CustomerDAO();
        System.out.println("Number of customers in the database: " + CustomerDAO.getAll().size());
        CustomerDAO.delete(selectedCustomer.getCustomerId());

        //Table Update
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        allCustomers.setAll(CustomerDAO.getAll());
        customerTableView.setItems(allCustomers);

    }


    @FXML
    void onSaveUpdate(ActionEvent event) throws SQLException {
        customerTableView.setDisable(false);
        Customer customer = new Customer(-0,"test","101 street","1234","22354", 1,"canadas");
        //Table Update
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        allCustomers.setAll(CustomerDAO.getAll());


        String customerName = customerNameField.getText();
        String customerAddress = customerAddressField.getText();
        String customerPostal = customerPostalCodeField.getText();
        String customerPhone = customerPHoneField.getText();
        int customerDivisionId  = countryCombo.getSelectionModel().getSelectedItem().getCountryId();
        String customerDivisionName = stateCombo.getSelectionModel().getSelectedItem().toString();
        System.out.println(customerName);


        customer.setCustomerName(customerName);
        customer.setAddress(customerAddress);
        customer.setPostalCode(customerPostal);
        customer.setPhone(customerPhone);
        customer.setDivisionId(customerDivisionId);
        customer.setDivisionName(customerDivisionName);

        CustomerDAO customerDao = new CustomerDAO();
        //System.out.println("Number of customers in the database: " + CustomerDAO.getAll().size());
        customerDao.add(customer);

        CustomerDAO.getCountryId(customerDivisionId);






        //Remove old customer record
        Customer deleteCustomer = currentlySelectedCustomer;
        CustomerDAO.delete(deleteCustomer.getCustomerId());
        System.out.println("Number of customers in the database: " + CustomerDAO.getAll().size());


        //Table Update
        //ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        allCustomers.setAll(CustomerDAO.getAll());
        customerTableView.setItems(allCustomers);
        setVisible(false);

        editMode = false;
        clearFields();

    }

    @FXML
    void onStateCombo(ActionEvent event) {

    }

    @FXML
    void onExitBtn(ActionEvent event) throws IOException {
        Parent customerScene = FXMLLoader.load(getClass().getResource("/lagasse/scheduler/main-view.fxml"));
        Scene scene = new Scene(customerScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


    @FXML
    void onAddNewCustomer(ActionEvent event) {
        setVisible(true);
        customerTableView.setDisable(true);
        saveNewCustomer.setVisible(true);
        saveUpdateButton.setVisible(false);
    }

}