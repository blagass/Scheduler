package lagasse.scheduler.model;

public class FirstLevelDivision {
    ///Class Variables///
    private int divisionId;
    private String division;
    ////////////////////////////////

    ///Forieng Keys//
    public int countryId;
    ////////////////////////////////

    /**
     * FirstLevel Division Constructor
     *
     * @param divisionId
     * @param division
     * @param countryId
     */
    public FirstLevelDivision(int divisionId, String division, int countryId){
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * division_id Getter
     * @return
     */
    public int getDivision_Id(){
        return divisionId;
    }

    /**
     * division Getter
     * @return
     */
    public String getDivision(){
        return division;
    }

    public int getCountryId(){ return countryId;}

    @Override
    public String toString(){
        return (division);
    }
}
