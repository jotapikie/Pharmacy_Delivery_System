package lapr.project.model;

public class User {

    private String name; // user's name
    
    private String password; // user's password
    
    private String email;

    /**
     * User's constructor to create instances of this class
     *
     * @param username - user's username
     * @param password - user's password
     */
    public User(String name,String email,  String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        
    }

    public String getName() { return name; } // returns the username of the user

    public String getPassword() { return password; } // returns the password of the user

    public String getEmail() { return email;}
    
    

    /**
     * Modifies the user's username to the value passed by parameter.
     *
     * @param username - user's new username.
     */
    void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Client's name is invalid.");
        this.name = name;
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
    
    void setEmail(String email) {
        if (email == null || email.isEmpty() || !email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new IllegalArgumentException("Pharmacy's email is invalid.");
        }
        this.email = email;
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
        return email.equalsIgnoreCase(user.email);
    }

    @Override
    public int hashCode() { return email.hashCode(); } // hashCode


}
