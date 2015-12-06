/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken;
import java.io.IOException;
import kenken.Domain.User;
import kenken.DomainManagers.UserManager;
/**
 *
 * @author dasilvacontin
 */
public class Kenken {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
        System.out.println("Hello world");
        User user;
        UserManager userManager = new UserManager();
        user = (User) userManager.findOneWith("username", "foo");
        if (user != null) {
            System.out.println(user.description());
            user.setUsername("dasilvacontin");
            System.out.println(user.description());
        }
        //userManager.save(user);
        
        /*
        Score[] scores;
        ScoreManager scoreManager = new ScoreManager();
        scores = scoreManager.findWith("username", user.getUsername());
        */
    }
    
}
