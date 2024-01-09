package lagasse.scheduler.model;

public class User {
    ///Class Variables///
    private int userId;
    private String userName;
    private String password;
    ////////////////////////////////

    /**
     * User Constructor
     * @param userId
     * @param userName
     * @param password
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * user_id Getter
     * @return user_id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * user_name Getter
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * password Getter
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
