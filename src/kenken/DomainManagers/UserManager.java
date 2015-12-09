/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;
import  kenken.Domain.User;
/**
 *
 * @author dasilvacontin
 */
public class UserManager extends DomainManager<User> {
    public UserManager() {
        super("./data/Domain/User.db", User.class);
    }
}