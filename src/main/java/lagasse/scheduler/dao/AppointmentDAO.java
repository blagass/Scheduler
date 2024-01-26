package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Appointment;
import lagasse.scheduler.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDAO {
    public static ObservableList<Appointment> getAll() throws SQLException {
        JDBC.openConnection();
        //DateTimeFormatter dtf = DateTimeFormatter.; // use this DTF to convert for parse
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        String sql = "SELECT Appointment_ID,Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID \n" +
                "from APPOINTMENTS";
//                "INNER JOIN CUSTOMERS \n" +
//                "ON APPOINTMENTS.Customer_ID = CUSTOMERS.Customer_ID"; //FINISH THIS
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            String appointmentStart = rs.getString("Start");
            String appointmentEnd = rs.getString("End");
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            LocalDateTime startLtd = LocalDateTime.parse(appointmentStart);
            LocalDateTime endLtd = LocalDateTime.parse(appointmentEnd);



            Appointment appointment = new Appointment(appointmentId,appointmentTitle,appointmentDescription,appointmentLocation,appointmentType, startLtd,endLtd,appointmentCustomerId,appointmentUserId,contactId);
            allAppointments.add(appointment);


        }
        return allAppointments; // Returns the observable list


    }

    public int add(Appointment appointment) throws SQLException{
        String sql = "INSERT INTO APPOINTMENTS(Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,appointment.getTitle());
        ps.setString(2,appointment.getDescription());
        ps.setString(3,appointment.getLocation());
        ps.setString(4,appointment.getType());
        ps.setTimestamp(5,appointment.getStart());
        ps.setTimestamp(6,appointment.getEnd());
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8,appointment.getUserId());
        ps.setInt(9,appointment.getContactId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    };

    public int delete(int appointmentId) throws SQLException{
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    };

}
