package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Appointment;
import lagasse.scheduler.model.Customer;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class AppointmentDAO {
    public LocalTime transferTime;
    public LocalDate transferDate;
    public static ObservableList<Appointment> getAll() throws SQLException {
        JDBC.openConnection();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // use this DTF to convert for parse
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        String sql = "SELECT Appointment_ID,Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID \n" +
                "from APPOINTMENTS";
//                "INNER JOIN CUSTOMERS \n" +
//                "ON APPOINTMENTS.Customer_ID = CUSTOMERS.Customer_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");


            //Create current system zone
            ZoneId currentZone = ZoneId.of(TimeZone.getDefault().getID());
            System.out.println(currentZone);

            //Create ZonedDateTime
            ZonedDateTime zdt = appointmentStart.atZone(currentZone);
            ZonedDateTime currentToLocalZDT = zdt.withZoneSameInstant(currentZone);
            appointmentStart = currentToLocalZDT.toLocalDateTime();
            System.out.println(appointmentStart);

            Appointment appointment = new Appointment(appointmentId,appointmentTitle,appointmentDescription,appointmentLocation,appointmentType, appointmentStart,appointmentEnd,appointmentCustomerId,appointmentUserId,contactId);
            allAppointments.add(appointment);


        }
        return allAppointments; // Returns the observable list


    }

    public static int add(Appointment appointment) throws SQLException{
        LocalDateTime start = appointment.getStart();
        Timestamp startTimeStamp = Timestamp.valueOf(start);

        LocalDateTime end = appointment.getEnd();
        Timestamp endTimeStamp = Timestamp.valueOf(end);

        String sql = "INSERT INTO APPOINTMENTS(Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,appointment.getTitle());
        ps.setString(2,appointment.getDescription());
        ps.setString(3,appointment.getLocation());
        ps.setString(4,appointment.getType());
        ps.setTimestamp(5,startTimeStamp);
        ps.setTimestamp(6,endTimeStamp); //work on these after you have the first part of appointments figured out
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8,appointment.getUserId());
        ps.setInt(9,appointment.getContactId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    };

    public static int delete(int appointmentId) throws SQLException{
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    };

}
