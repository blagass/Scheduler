package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Appointment;
import lagasse.scheduler.model.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDAO {

    ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public int add(Appointment appointment) throws SQLException{
        String sql = "INSERT INTO CUSTOMERS(Customer_Name,Address,Postal_Code, Phone, Division_ID) VALUES(?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,appointment.getTitle());
        ps.setString(2,appointment.getDescription());
        ps.setString(3,appointment.getLocation());
        ps.setString(4,appointment.getType());
        ps.setD(5,appointment.getStart());
        ps.setD(6,appointment.getEnd());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    };

    public int delete(int customerId) throws SQLException{
        return 0;
    };

}
