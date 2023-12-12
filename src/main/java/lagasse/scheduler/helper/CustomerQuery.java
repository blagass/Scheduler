package lagasse.scheduler.helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import lagasse.scheduler.controller.CustomerView;
import lagasse.scheduler.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerQuery {


    /**
     * DB call to insert new customer
     * @param customerName
     * @param divisionId
     * @return
     * @throws SQLException
     */
    public static int insert(String customerName,int divisionId) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS(Customer_Name,Division_ID) VALUES(?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setInt(2,divisionId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * DB call to update Customer by ID
     * @param customerName
     * @param customerId
     * @return
     * @throws SQLException
     */
    public static int update(String customerName, int customerId) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setInt(2,customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * DB call to delete customer by ID
     * @param customerId
     * @return
     * @throws SQLException
     */
    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * DB call to select all customers
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int customerDivision = rs.getInt("Division_ID");

            Customer customer = new Customer(customerId,customerName,customerAddress,customerPostalCode,customerPhone,customerDivision);
            allCustomers.add(customer);

        }
        return allCustomers; // Returns the observable list

    }

    /**
     * DB call to select all customers that match the division id.
     * @param divisionId
     * @throws SQLException
     */
    public static void select(int divisionId) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt ( 1, divisionId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            int divisionIdFK = rs.getInt("Division_ID");
            System.out.print(customerId + " | ");
            System.out.print(customerName + " | ");
            System.out.print(divisionIdFK + "\n");
        }
    }



}