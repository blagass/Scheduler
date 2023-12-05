package lagasse.scheduler.model;

public class User {
    ///Class Variables///
    private int user_id;
    private String user_name;
    private String password;
    ////////////////////////////////

    /**
     * User Constructor
     * @param user_id
     * @param user_name
     * @param password
     */
    public User(int user_id, String user_name, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
    }

    /**
     * user_id Getter
     * @return user_id
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * user_name Getter
     * @return user_name
     */
    public String getUserName() {
        return user_name;
    }

    /**
     * password Getter
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
