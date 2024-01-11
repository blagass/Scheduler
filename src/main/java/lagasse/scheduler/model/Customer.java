package lagasse.scheduler.model;

public class Customer {
    ///Class Variables///
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;


    ////////////////////////////////

    ///Foreign Keys///
    public int divisionId;
    ////////////////////////////////

    /**
     * Customer constructor
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;

        this.divisionId = divisionId;
    }

    /**
     * customer_id Getter
     * @return customer_id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * customer_name Getter
     * @return customer_name
     */
    public String getCustomerName(){
        return customerName;
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
        return postalCode;
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
    public int getDivisionId(){return divisionId;}
}
