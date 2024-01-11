package lagasse.scheduler.model;

public class Country {
    ///Class Variables///
    private int countryId;
    private String country;
    ////////////////////////////////

    /**
     * Country Constructor
     * @param countryId
     * @param country
     */
    public Country(int countryId, String country){
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * country_id Getter
     * @return country_id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * country Getter
     * @return country
     */
    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return (country);
    }
}
