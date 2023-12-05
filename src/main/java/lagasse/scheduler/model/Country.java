package lagasse.scheduler.model;

public class Country {
    ///Class Variables///
    private int country_id;
    private String country;
    ////////////////////////////////

    /**
     * Country Constructor
     * @param country_id
     * @param country
     */
    public Country(int country_id, String country){
        this.country_id = country_id;
        this.country = country;
    }

    /**
     * country_id Getter
     * @return country_id
     */
    public int getCountryId() {
        return country_id;
    }

    /**
     * country Getter
     * @return country
     */
    public String getCountry() {
        return country;
    }
}
