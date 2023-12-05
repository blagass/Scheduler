package lagasse.scheduler.model;

public class Contact {
    ///Class Variables///
    private int contact_id;
    private String contact_name;
    private String contact_email;
    ////////////////////////////////

    /**
     * Contact Constructor
     * @param contact_id
     * @param contact_name
     * @param contact_email
     */
    public Contact(int contact_id, String contact_name, String contact_email) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.contact_email = contact_email;

    }

    /**
     * contact_id Getter
     * @return contact_id
     */
    public int getContactId() {
        return  contact_id;
    }

    /**
     * contact_name Getter
     * @return contact_name
     */
    public String getContactName() {
        return contact_name;
    }

    /**
     * contact_email Getter
     * @return contact_email
     */
    public String getContactEmail() {
        return contact_email;
    }
}
