package lagasse.scheduler.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lagasse.scheduler.dao.UserDAO;
import lagasse.scheduler.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginView implements Initializable {
    public TextField userField;
    public TextField loginField;
    public Label zoneLabel;
    public Label zoneIdLabel;
    public Label loginText;
    public Button loginButton;
    public Label userLabel;
    public Label passwordLabel;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String zone = String.valueOf(ZoneId.systemDefault());
        zoneLabel.setText(zone);

        ResourceBundle rb = ResourceBundle.getBundle("lagasse/scheduler/Lang", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr"))
            System.out.println(rb.getString("hello"));
        zoneIdLabel.setText(rb.getString("hello"));
        loginText.setText(rb.getString("log"));
        loginButton.setText(rb.getString("log"));
        userLabel.setText(rb.getString("user"));
        passwordLabel.setText(rb.getString("password"));
       zoneLabel.setText(zone);
    }
}
