package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Contact;
import lagasse.scheduler.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {
    public static ObservableList<Contact> getAll() throws SQLException {
        JDBC.openConnection();
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM CONTACTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");


            Contact contact = new Contact(contactId,contactName,email);
            allContacts.add(contact);

        }
        return allContacts; // Returns the observable list

    }

}
