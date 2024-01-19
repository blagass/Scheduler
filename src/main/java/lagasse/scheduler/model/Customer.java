package lagasse.scheduler.model;

public class Customer {
    ///Class Variables///
    private int customerId;
    private String customerName;
    private String address;




    private String postalCode;
    private String phone;

    private String divisionName;



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
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId,String divisionName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    /**
     * customer_id Getter
     * @return customer_id
     */
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    /**
     * customer_name Getter
     * @return customer_name
     */
    public String getCustomerName(){
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * address Getter
     * @return address
     */
    public String getAddress(){
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * postal_code Getter
     * @return postal_code
     */
    public String getPostalCode(){
        return postalCode;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * phone Getter
     * @return phone
     */
    public String getPhone(){
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public int getDivisionId(){return divisionId;}

    public String getDivisionName(){return divisionName;}

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }


}
