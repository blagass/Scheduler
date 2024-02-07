package lagasse.scheduler.model;

public class Contact {
    ///Class Variables///
    private int contactId;
    private String contactName;
    private String contactEmail;
    ////////////////////////////////

    /**
     * Contact Constructor
     * @param contactId
     * @param contactName
     * @param contactEmail
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;

    }

    /**
     * contact_id Getter
     * @return contact_id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * contact_name Getter
     * @return contact_name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * contact_email Getter
     * @return contact_email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public String toString(){
        return (contactName);
    }
}
