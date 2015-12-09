/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Views;

import kenken.Domain.User;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class MainMenuView extends ActionListView {
    private User loggedUser;
    
    public MainMenuView(User loggedUser) {
        super(Arrays.asList(
            "New Game",
            "Continue Game",
            "Design Board",
            "Ranking",
            "Manage User",
            "Quit"
        ), Arrays.asList(
            Arrays.asList("present", "new-game"),
            Arrays.asList("present", "continue-game"),
            Arrays.asList("present", "design-board"),
            Arrays.asList("present", "ranking"),
            Arrays.asList("present", "user-config"),
            Arrays.asList("back")
        ));
        setTitle("Main Menu");
        this.loggedUser = loggedUser;
    }
    
    public void run() {
        System.out.println("Logged in as '" + loggedUser.getUsername() + "'.");
        System.out.println();
        super.run();
    }
    
}
