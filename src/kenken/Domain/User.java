/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class User extends DomainBase {
    private String username;
    private String password;
    
    /**
     *
     * @param data
     */
    public User(List<String> data) {
        super(data);
        this.username = data.get(0);
        this.password = data.get(1);
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
    public String description() {
        return "username: " + this.username + ", password: " + this.password;
    }
}
