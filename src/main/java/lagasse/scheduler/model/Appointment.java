package lagasse.scheduler.model;
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

    ///Foriegn Keys///
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
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
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
    public LocalDateTime getStart(){
        return start;
    }

    /**
     * end Getter
     * @return end
     */
    public LocalDateTime getEnd(){
        return end;
    }
}
