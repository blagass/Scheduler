package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Country;
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
}
