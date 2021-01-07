package lapr.project.model;

public class User {

    private String username; // user's username

    private String password; // user's password

    /**
     * User's constructor to create instances of this class
     *
     * @param username - user's username
     * @param password - user's password
     */
    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() { return username; } // returns the username of the user

    public String getPassword() { return password; } // returns the password of the user

    /**
     * Modifies the user's username to the value passed by parameter.
     *
     * @param username - user's new username.
     */
    void setUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Client's username is invalid.");
        this.username = username;
    }

    /**
     * Modifies the user's password to the value passed by parameter.
     *
     * @param password - user's new password.
     */
    void setPassword(String password) {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Client's password is invalid.");
        this.password = password;
    }

    /**
     * Boolean method to check if this user is equal to the user passed by parameter.
     *
     * @param o - instance to be checked wether it's equal or not.
     * @return - true if they're equal, false if they're not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return username.equalsIgnoreCase(user.username);
    }

    @Override
    public int hashCode() { return username.hashCode(); } // hashCode
}
