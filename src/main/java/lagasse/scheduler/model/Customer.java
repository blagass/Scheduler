package lagasse.scheduler.model;

public class Customer {
    ///Class Variables///
    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    ////////////////////////////////

    ///Foreign Keys///
    public int division_id;
    ////////////////////////////////

    /**
     * Customer constructor
     * @param customer_id
     * @param customer_name
     * @param address
     * @param postal_code
     * @param phone
     */
    public Customer(int customer_id, String customer_name, String address, String postal_code, String phone, int division_id) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.division_id = division_id;
    }

    /**
     * customer_id Getter
     * @return customer_id
     */
    public int getCustomerId() {
        return customer_id;
    }

    /**
     * customer_name Getter
     * @return customer_name
     */
    public String getCustomerName(){
        return customer_name;
    }

    /**
     * address Getter
     * @return address
     */
    public String getAddress(){
        return address;
    }

    /**
     * postal_code Getter
     * @return postal_code
     */
    public String getPostalCode(){
        return postal_code;
    }

    /**
     * phone Getter
     * @return phone
     */
    public String getPhone(){
        return phone;
    }

    /**
     *
     * @return
     */
    public int getDivisionId(){return division_id;}
}
