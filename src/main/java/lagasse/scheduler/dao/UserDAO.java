package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Customer;
import lagasse.scheduler.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    User loggedInUser = null;

    public static ObservableList<User> getAll() throws SQLException {
        JDBC.openConnection();
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        String sql = "SELECT * USER";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");


            User user = new User(userId,userName,password);
            allUsers.add(user);

        }
        return allUsers; // Returns the observable list

    }

}
