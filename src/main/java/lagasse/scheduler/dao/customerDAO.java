package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerDAO implements DAO<Customer>{
    @Override
    public ObservableList<Customer> getAll() throws SQLException {
        JDBC.openConnection();
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

    @Override
    public Customer get(int id) throws SQLException {
    //WORK ON THIS NEXT
            String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt ( 1, id);
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

    @Override
    public int insert(Customer customer) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Customer customer) throws SQLException {
        return 0;
    }
}
