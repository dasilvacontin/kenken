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
public class UserManager extends DomainManager {

    public UserManager() {
        System.out.println("UserManager constructor");
        keyCount = 2;
        dbPath = "./data/Domain/User.db";
        managedClass = User.class;
    }
    
    @Override
    int indexForKey(String key) {
        switch (key) {
            case "username":
                return 0;
            case "password":
                return 1;
            default:
                return -1;
        }
    }
}