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
import java.util.ResourceBundle;

public class CustomerView implements Initializable {
    @FXML
    private Button exitBtn;

    @FXML
    private Button saveUpdateButton;

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


    //transfer custoemr variables
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
//        //Country/State
//
//        ObservableList<FirstLevelDivision>usDivisions = FXCollections.observableArrayList();
//        ObservableList<FirstLevelDivision>canadaDivisions = FXCollections.observableArrayList();
//        ObservableList<FirstLevelDivision>ukDivisions = FXCollections.observableArrayList();
//
//        usDivisions.setAll(FirstLevelDivisionDAO.usStates());
//        canadaDivisions.setAll(FirstLevelDivisionDAO.canadaStates());
//        ukDivisions.setAll(FirstLevelDivisionDAO.ukStates());
//
//
//
//        Country countryComboSelect = countryCombo.getSelectionModel().getSelectedItem();
//        if(countryComboSelect.getCountryId() == 1){
//            stateCombo.setItems(usDivisions);
//
//        } else if (countryComboSelect.getCountryId() == 2) {
//            stateCombo.setItems(ukDivisions);
//
//        } else if (countryComboSelect.getCountryId() == 3) {
//            stateCombo.setItems(canadaDivisions);
//
//        } else{
//            System.out.println("Select a Country");
//        }

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
        //String customerDivisionName = stateCombo.getSelectionModel().getSelectedItem().toString();
        System.out.println(customerName);


        customer.setCustomerName(customerName);
        customer.setAddress(customerAddress);
        customer.setPostalCode(customerPostal);
        customer.setPhone(customerPhone);
        customer.setDivisionId(customerDivisionId);
        //customer.setDivisionName(customerDivisionName);


//        System.out.println(customerName);
//    System.out.println(customer.getCustomerName());
//    System.out.println(customer.getCustomerId());
//        System.out.println(customer.getAddress());
//        System.out.println(customer.getPostalCode());
//        System.out.println(customer.getPhone());
//        System.out.println(customer.getDivisionId());
//        //System.out.println(customer.getDivisionName());

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

        Customer customer = new Customer(-0,"test","101 street","1234","22354", 1,"canadas");
        //Table Update
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        allCustomers.setAll(CustomerDAO.getAll());
        customerTableView.setItems(allCustomers);

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
        System.out.println("Number of customers in the database: " + CustomerDAO.getAll().size());
        customerDao.add(customer);

        //Remove old customer record
        Customer deleteCustomer = currentlySelectedCustomer;
        CustomerDAO.delete(deleteCustomer.getCustomerId());
        System.out.println("Number of customers in the database: " + CustomerDAO.getAll().size());

//        //Table Update
//        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
//        allCustomers.setAll(CustomerDAO.getAll());
//        customerTableView.setItems(allCustomers);

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

}