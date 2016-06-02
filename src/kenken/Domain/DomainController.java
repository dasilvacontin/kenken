/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

import java.util.Arrays;
import java.util.List;
import kenken.DomainManagers.*;
import kenken.Views.ActionListView;
/**
 *
 * @author dasilvacontin
 */
public class DomainController {
    UserManager userManager;
    
    public DomainController() {
        userManager = new UserManager();
    }
    
    public User doLogin(String username, String password) throws Exception {
        User user = userManager.findOneWith("username", username);
        if (user == null) {
            throw new Exception("User not found.");
        }
        Boolean passwordMatches = user.getPassword().equals(password);
        if (!passwordMatches) {
            throw new Exception("Incorrect password.");
        }
        return user;
    }
    
    public User doRegister(String username, String password) throws Exception {
        User user = new User(username, password);
        user.validate();
        User userWithSameUsername = userManager.findOneWith("username", username);
        if (userWithSameUsername != null) {
            throw new Exception("Username is already in use.");
        }
        userManager.save(user);
        return user;
    }
    
    public Game newGameRandomBoard(User player, int size) throws Exception {
        Board b = new Board(null, size);
        b.validate();
        b.generateRandomBoard();
        Game g = new Game(player.getUsername(), b);
        return g;
    }
    
    public Board designNewBoard(User designer, int size) throws Exception {
        Board b = new Board(designer.getUsername(), size);
        throw new Exception("Not yet implemented.");
        //return b;
    }
}
