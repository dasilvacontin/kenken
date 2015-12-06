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
        return username + " " + password;
    }
    
    public static User deserialize(String str) {
        String[] props = str.split(" ");
        String username = props[0];
        String password = props[1];
        User u = new User(username, password);
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

    /**
     *
     * @return description string
     */
    @Override
    public String toString() {
        return "[User username: " + username + ", password: " + password + "]";
    }
}
