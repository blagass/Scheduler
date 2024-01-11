package lagasse.scheduler.helper;

import lagasse.scheduler.dao.CustomerDAO;
import lagasse.scheduler.model.Customer;

import java.sql.SQLException;

public class CustomerDaoTester {
    public static boolean testAdd(){
        try {
            Customer customer = new Customer(-1, "bob", "main st", "absd123", "1234567890",  2);
            CustomerDAO customerDao = new CustomerDAO();
            System.out.println("Number of customers in the database: " + customerDao.getAll().size());
            //customerDao.add(customer);
            //customerDao.delete(5);

            System.out.println("Number of customers in the database: " + customerDao.getAll().size());
            for (Customer customer1:customerDao.getAll()){
                System.out.println(customer1.getCustomerId() + ", " + customer1.getCustomerName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static boolean testUpdate(){
        return false;
    }
}
