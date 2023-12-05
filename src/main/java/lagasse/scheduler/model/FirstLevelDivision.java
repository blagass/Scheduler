package lagasse.scheduler.model;

public class FirstLevelDivision {
    ///Class Variables///
    private int division_id;
    private String division;
    ////////////////////////////////

    ///Forieng Keys///
    public int country_id;
    ////////////////////////////////

    /**
     * FirstLevel Division Constructor
     * @param division_id
     * @param division
     */
    public FirstLevelDivision(int division_id, String division){
        this.division_id = division_id;
        this.division = division;
    }

    /**
     * division_id Getter
     * @return
     */
    public int getDivision_Id(){
        return division_id;
    }

    /**
     * division Getter
     * @return
     */
    public String getDivision(){
        return division;
    }
}
