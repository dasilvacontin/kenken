/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Views;

import kenken.Utils;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author dasilvacontin
 */
public class HomeView extends ActionListView {

    public HomeView() {
        super(null, null);
        actions = Utils.asLinkedList(
                "Register",
                "Login",
                "Quit"
        );
        consequences = Utils.asLinkedList(
                Utils.asLinkedList("present", "register"),
                Utils.asLinkedList("present", "login"),
                Utils.asLinkedList("back")
        );
    }
    
    @Override
    public String getTitle() {
        return "Home";
    }
}


