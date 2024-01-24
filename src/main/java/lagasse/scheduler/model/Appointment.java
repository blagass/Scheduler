package lagasse.scheduler.model;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {
    ///Class Variables///
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    ////////////////////////////////

    ///Foreign Keys///
    public int customerId;


    public int userId;
    public int contactId;
    ////////////////////////////////

    /**
     * Appointment Constructor
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,int customerId,int userId,int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * appointment_id Getter
     * @return appointment_id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * title Getter
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * description Getter
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * location Getter
     * @return location
     */
    public String getLocation(){
        return location;
    }

    /**
     * type getter
     * @return type
     */
    public String getType(){
        return type;
    }

    /**
     * start Getter
     *
     * @return start
     */
    public Timestamp getStart(){
        return null;
    }


    /**
     * end Getter
     * @return end
     */
    public Timestamp getEnd(){
        return null;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

}
