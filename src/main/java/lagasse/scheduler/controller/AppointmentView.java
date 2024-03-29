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
import lagasse.scheduler.dao.*;
import lagasse.scheduler.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private TableColumn<Appointment, LocalDateTime> appStartCol;

    @FXML
    private TableColumn<?, ?> appTitleCol;

    @FXML
    private TableColumn<?, ?> appTypeCol;

    @FXML
    private TableColumn<?, ?> appUserCol;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TextField appIdField;


    @FXML
    private TextField titleField;
    @FXML
    private TextField contactField;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<LocalTime> endCombo;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField locationField;

    @FXML
    private ComboBox<LocalTime> startCombo;

    @FXML
    private DatePicker startDatePicker;


    @FXML
    private TextField typeField;

    @FXML
    private TextField userIdField;

    @FXML
    private ComboBox<Contact> contactCombo;


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
        ObservableList<Appointment>transferAppointment = FXCollections.observableArrayList();


        ObservableList<LocalTime> hours = FXCollections.observableArrayList();

        //Lambda
        Runnable addHours = () -> {
            for (int hour = 0; hour < 24; hour++) {
                for (int minute = 0; minute < 60; minute += 30) {
                    hours.add(LocalTime.of(hour, minute));
                }
            }
        };


        addHours.run();


        System.out.println("Hours in the list: " + hours);


        try {
            transferCustomer.setAll(CustomerDAO.getAll());
            transferUser.setAll(UserDAO.getAll());
            transferContact.setAll(ContactDAO.getAll());
            transferAppointment.setAll(AppointmentDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appUserCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        //Set up combo boxes
        startCombo.setItems(hours);
        endCombo.setItems(hours);
        appointmentTable.setItems(transferAppointment);


        contactCombo.setItems(transferContact);

    }


    @FXML
    void onEndCombo(ActionEvent event) {

    }

    @FXML
    void onEndDatePicker(ActionEvent event) {

    }

    @FXML
    void onStartCombo(ActionEvent event) {

    }

    @FXML
    void onStartDatePicker(ActionEvent event) {

    }
    @FXML
    void onAppointmentEdit(ActionEvent event) {

    }

    @FXML
    void onAppointmentSave(ActionEvent event) throws SQLException {

        //Observable transfer appointment list
        ObservableList<Appointment>transferAppointment = FXCollections.observableArrayList();

        int appointmentId = 0;
    String title = titleField.getText();
    String description = descriptionField.getText();
    String location = locationField.getText();
    String type = typeField.getText();
    Contact contact = contactCombo.getSelectionModel().getSelectedItem();
    int customerId = Integer.parseInt(customerIdField.getText());
    int userId = Integer.parseInt(userIdField.getText());
    LocalTime startTime = startCombo.getSelectionModel().getSelectedItem();
    LocalDate startDate = startDatePicker.getValue();
    LocalTime endTime = endCombo.getSelectionModel().getSelectedItem();
    LocalDate endDate = endDatePicker.getValue();

    LocalDateTime startLtd = LocalDateTime.of(startDate,startTime);
    LocalDateTime endLtd = LocalDateTime.of(endDate,endTime);

    int contactId =  contact.getContactId();

    Appointment appointment = new Appointment(appointmentId,title,description,location,type,startLtd, endLtd,customerId,userId,contactId);
        System.out.println(appointment.getAppointmentId());

        AppointmentDAO.add(appointment);
        transferAppointment.setAll(AppointmentDAO.getAll());
        appointmentTable.setItems(transferAppointment);

    }

    @FXML
    void onAppointmentDelete(ActionEvent event) throws SQLException {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        System.out.println("Number of customers in the database: " + AppointmentDAO.getAll().size());
        AppointmentDAO.delete(selectedAppointment.getAppointmentId());
        System.out.println("Number of customers in the database: " + AppointmentDAO.getAll().size());

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        allAppointments.setAll(AppointmentDAO.getAll());
        appointmentTable.setItems(allAppointments);
    }

}
