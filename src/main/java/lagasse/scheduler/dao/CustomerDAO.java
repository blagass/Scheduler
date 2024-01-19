package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.controller.CustomerView;
import lagasse.scheduler.model.Customer;
import lagasse.scheduler.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO{


    public static ObservableList<Customer> getAll() throws SQLException {
        JDBC.openConnection();
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        String sql = "SELECT CUSTOMERS.Customer_ID, CUSTOMERS.Customer_Name, CUSTOMERS.Address, CUSTOMERS.Postal_Code, CUSTOMERS.Phone, CUSTOMERS.Division_ID, FIRST_LEVEL_DIVISIONS.Division \n" +
                "from CUSTOMERS \n" +
                "INNER JOIN  FIRST_LEVEL_DIVISIONS \n" +
                "ON CUSTOMERS.Division_ID = FIRST_LEVEL_DIVISIONS.Division_ID"; //edited last
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int customerDivision = rs.getInt("Division_ID");
            String customerDivisionName = rs.getString("Division");


            Customer customer = new Customer(customerId,customerName,customerAddress,customerPostalCode,customerPhone,customerDivision,customerDivisionName);
            allCustomers.add(customer);


        }
        return allCustomers; // Returns the observable list


    }


    public int add(Customer customer) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS(Customer_Name,Address,Postal_Code, Phone, Division_ID) VALUES(?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,customer.getCustomerName());
        ps.setString(2,customer.getAddress());
        ps.setString(3,customer.getPostalCode());
        ps.setString(4,customer.getPhone());
        ps.setInt(5,customer.getDivisionId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }


    public int delete(int customerId) throws SQLException {

        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}