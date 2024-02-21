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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lagasse.scheduler.dao.AppointmentDAO;
import lagasse.scheduler.dao.ContactDAO;
import lagasse.scheduler.dao.CustomerDAO;
import lagasse.scheduler.dao.UserDAO;
import lagasse.scheduler.model.Appointment;
import lagasse.scheduler.model.Contact;
import lagasse.scheduler.model.Customer;
import lagasse.scheduler.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReportView implements Initializable {

    public ComboBox contactComboBox;
    public TableView appointmentTable;
    public TableColumn appIdCol;
    public TableColumn appTitleCol;
    public TableColumn appDescriptionCol;
    public TableColumn appTypeCol;
    public TableColumn appStartCol;
    public TableColumn appStartTimeCol;
    public TableColumn appStartDateCol;
    public TableColumn appEndDateCol;
    public TableColumn appEndTimeCol;
    public TableColumn appCustomerCol;
    public TableColumn appLocationCol;
    public TableColumn appUserCol;
    public TableColumn appContactCol;
    public ComboBox typeComboBox;
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

        ObservableList<Customer> transferCustomers = FXCollections.observableArrayList();
        ObservableList<User>transferUsers = FXCollections.observableArrayList();
        ObservableList<Contact>transferContacts = FXCollections.observableArrayList();
        ObservableList<Appointment>transferAppointments = FXCollections.observableArrayList();

        try {
            transferCustomers.setAll(CustomerDAO.getAll());
            transferUsers.setAll(UserDAO.getAll());
            transferContacts.setAll(ContactDAO.getAll());
            transferAppointments.setAll(AppointmentDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        try {
//            transferContacts.setAll(ContactDAO.getAll());
//
//            // Extract contact names
//            ObservableList<String> contactNames = transferContacts.stream()
//                    .map(Contact::getContactName)
//                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
//
//            typeComboBox.setItems(contactNames);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        ObservableList<String> allTypes = FXCollections.observableArrayList();
        try {
            for (Appointment appt : AppointmentDAO.getAll()) {
                if (!allTypes.contains(appt.getType())) {
                    allTypes.add(appt.getType());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        typeComboBox.setItems(allTypes);


        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartDateCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndDateCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appUserCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentTable.setItems(transferAppointments);

        contactComboBox.setItems(transferContacts);
    }

    public void onByType(ActionEvent actionEvent) throws SQLException {
        appointmentTable.setItems(AppointmentDAO.getAll());

        ObservableList<Appointment> allAppointments = appointmentTable.getItems();

        // Get the selected type from a ComboBox (you'll need to add this to your FXML)
        String selectedType = (String) typeComboBox.getValue();

        // Lambda expression with string comparison
        ObservableList<Appointment> filteredAppointments = allAppointments.filtered(
                appointment -> appointment.getType().equals(selectedType)
        );

        appointmentTable.setItems(filteredAppointments);
    }

    public void onByMonth(ActionEvent actionEvent) throws SQLException {
        appointmentTable.setItems(AppointmentDAO.getAll());
        ObservableList<Appointment> allAppointments = appointmentTable.getItems();

        // Lambda expression for filtering
        ObservableList<Appointment> filteredAppointments = allAppointments.filtered(
                appointment -> appointment.getStart().getMonthValue() == LocalDateTime.now().getMonthValue()
        );

        appointmentTable.setItems(filteredAppointments);
    }

    public void onType(MouseEvent mouseEvent) throws SQLException {

    }

    public void onTypeMouseClick(MouseEvent mouseEvent) throws SQLException {
        appointmentTable.setItems(AppointmentDAO.getAll());
        ObservableList<Appointment> allAppointments = appointmentTable.getItems();

        String selectedContactName = (String) typeComboBox.getValue();

        ObservableList<Appointment> filteredAppointments = allAppointments.filtered(
                appointment -> appointment.getType().equals(selectedContactName)
        );

        appointmentTable.setItems(filteredAppointments);


    }
}
