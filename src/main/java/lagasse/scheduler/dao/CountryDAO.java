package lagasse.scheduler.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lagasse.scheduler.model.Country;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {

    public static ObservableList<Country> getAll() throws SQLException {
        JDBC.openConnection();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM COUNTRIES";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            Country country = new Country(countryId,countryName);
            allCountries.add(country);

        }
        return allCountries; // Returns the observable list

    }

    public static ObservableList<Country> select(int divisionId) throws SQLException {
        ObservableList<Country> selectedCountry = FXCollections.observableArrayList();
        String sql = "SELECT * FROM COUNTRIES WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt ( 1, divisionId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            Country country = new Country(countryId,countryName);
            selectedCountry.add(country);
        }

        return selectedCountry;
    }

}
