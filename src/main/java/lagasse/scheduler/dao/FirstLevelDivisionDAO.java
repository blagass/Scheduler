package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionDAO {

    public static ObservableList<FirstLevelDivision> getAll() throws SQLException {
        JDBC.openConnection();
        ObservableList<FirstLevelDivision> allStates = FXCollections.observableArrayList();
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int stateId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(stateId,division,countryId);
            allStates.add(firstLevelDivision);
        }
        return allStates; // Returns the observable list
    }

    public static ObservableList<FirstLevelDivision> usStates() throws SQLException {
        JDBC.openConnection();
        ObservableList<FirstLevelDivision> allUsStates = FXCollections.observableArrayList();
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Country_ID = 1";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int stateId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(stateId,division,countryId);
            allUsStates.add(firstLevelDivision);
        }
        return allUsStates; // Returns the observable list
    }

    public static ObservableList<FirstLevelDivision> ukStates() throws SQLException {
        JDBC.openConnection();
        ObservableList<FirstLevelDivision> allUkStates = FXCollections.observableArrayList();
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Country_ID = 2";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int stateId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(stateId,division,countryId);
            allUkStates.add(firstLevelDivision);
        }
        return allUkStates; // Returns the observable list
    }

    public static ObservableList<FirstLevelDivision> canadaStates() throws SQLException {
        JDBC.openConnection();
        ObservableList<FirstLevelDivision> allCanadaStates = FXCollections.observableArrayList();
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Country_ID = 3";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int stateId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(stateId,division,countryId);
            allCanadaStates.add(firstLevelDivision);
        }
        return allCanadaStates; // Returns the observable list
    }


}
