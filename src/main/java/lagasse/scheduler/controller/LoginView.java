package lagasse.scheduler.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lagasse.scheduler.dao.UserDAO;
import lagasse.scheduler.model.User;

import java.io.IOException;
import java.sql.SQLException;



public class LoginView {
    public TextField userField;
    public TextField loginField;

    public void onLogin(ActionEvent actionEvent) throws SQLException, IOException {

        String userName = userField.getText();

        String password = loginField.getText();

        User user = new User(-1,userName,password);

        ObservableList<User> allUsers;
        allUsers = UserDAO.getAll();

        boolean loginSuccess = allUsers.stream()
                .anyMatch(User -> user.getUserName().equals(userName) &&
                        user.getPassword().equals(password));

        if (loginSuccess) {
            System.out.println("Success!");
            Parent customerScene = FXMLLoader.load(getClass().getResource("/lagasse/scheduler/main-view.fxml"));
            Scene scene = new Scene(customerScene);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            System.out.println("Failed Login");
        }

    }
}
