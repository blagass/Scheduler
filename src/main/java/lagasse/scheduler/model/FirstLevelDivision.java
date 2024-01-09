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
     * @param divisionId
     * @param division
     */
    public FirstLevelDivision(int divisionId, String division){
        this.divisionId = divisionId;
        this.division = division;
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
}
