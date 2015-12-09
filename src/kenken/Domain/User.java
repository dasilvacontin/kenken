/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

/**
 *
 * @author dasilvacontin
 */
public class User extends DomainBase {
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    public String serialize() {
        return super.serialize() + " " + username + " " + password;
    }
    
    public static User deserialize(String[] props) {
        String id = props[0];
        String username = props[1];
        String password = props[2];
        User u = new User(username, password);
        u._id = id;
        return u;
    }
    
    @Override
    public Boolean matchesQuery(String key, String value) {
        return key.equals("username") && value.equals(username);
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void validate() throws Exception {
        if (username == null) {
            throw new Exception("Missing username.");
        } else if (username.length() < 6 || 16 < username.length()) {
            throw new Exception("Username length must be between 6 and 16 chars.");
        } else if (password == null) {
            throw new Exception("Missing password.");
        } else if (password.length() < 3 || 25 < password.length()) {
            throw new Exception("Password length must be between 3 and 25 chars.");
        }
    }

    /**
     *
     * @return description string
     */
    @Override
    public String toString() {
        return "[User _id: " + _id + ", username: " + username + ", password: " + password + "]";
    }
}
