/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken;
import java.util.List;
import kenken.Domain.*;
import kenken.DomainManagers.*;
/**
 *
 * @author dasilvacontin
 */
public class Kenken {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello world");
        
        User user;
        UserManager userManager = new UserManager();
        user = (User) userManager.findOneWith("username", "dasilvacontin");
        if (user != null) {
            System.out.println(user);
            user.setUsername("flurry");
            System.out.println(user);
            
            List<Score> scores;
            ScoreManager scoreManager = new ScoreManager();
            scores = scoreManager.findWhere("username", user.getUsername());
            scores.forEach((score) -> {
                System.out.println(score);
            });
            
            userManager.save(user);
        }
    }
    
}
