/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import kenken.Domain.*;
import kenken.DomainManagers.*;
import kenken.Views.*;
import kenken.Utils;
/**
 *
 * @author dasilvacontin
 */
public class Kenken implements ViewDelegate {
    private final DomainController domainController;
    private final List<View> viewStack;
    private View currentView;
    private final UserManager userManager;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kenken game = new Kenken();
        game.start();
    }
    
    public Kenken() {
        domainController = new DomainController();
        viewStack = new LinkedList<>();
        userManager = new UserManager();
    }
    
    public void start() {
        System.out.println("#####################");
        System.out.println("# KENKEN terminal   #");
        System.out.println("# by David da Silva #");
        System.out.println("#####################");
        presentView(new HomeView());
    }
    
    private void renderStateTitle() {
        String stateTitle = "# ";
        Boolean first = true;
        for (View view : viewStack) {
            if (!first) {
                stateTitle += " > ";
            }
            first = false;
            stateTitle += view.getTitle();
        }
        System.out.println("");
        System.out.println(stateTitle);
        System.out.println("");
    }
    
    private void presentView(View view) {
        viewStack.add(view);
        view.delegate = this;
        renderView(view);
    }
    
    private void renderView(View view) {
        currentView = view;
        renderStateTitle();
        view.run();
    }
    
    private View getViewFromAction(List<String> action) {
        String viewId = action.get(0);
        View view = null;
        List<String> inputs;
        switch (viewId) {
            case "login":
                inputs = Arrays.asList(
                    "Username",
                    "Password"
                );        
                view = new InputListView("Login", "login", inputs);
                break;
                
            case "register":
                inputs = Arrays.asList(
                    "Username",
                    "Password",
                    "Re-Password"
                );        
                view = new InputListView("Register", "register", inputs);
                break;
                
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
        return view;
    }

    // pops a view from the view stack, presenting the previous one
    private void popView() {
        if (viewStack.size() >= 2) {
            viewStack.remove(viewStack.size() - 1); // pop view
            View view = viewStack.get(viewStack.size() - 1);
            renderView(view);
        } else {
            // exit the game
            System.out.println();
            System.out.println("See you soon! ʕ•ᴥ•ʔ");
            System.out.println();
        }
    }
    
    // ditch current view and show a provided one
    private void switchView(View view) {
        viewStack.remove(viewStack.size() - 1); // pop view
        presentView(view);
    }
    
    private void doLogin(List<String> params) {
        String username = params.get(0);
        String password = params.get(1);
        try {
            domainController.doLogin(username, password);
            // GO TO MAIN MENU
        } catch (Exception ex) {
            currentView.showErrorMessage(ex.getMessage());
            List<String> actions = Arrays.asList("Retry", "Back");
            List<List<String>> consequences = Utils.asLinkedList(
                Utils.asLinkedList("switch", "login"),
                Utils.asLinkedList("back")
            );

            ActionListView retryView = new ActionListView(actions, consequences);
            retryView.setTitle("Login - Error");
            switchView(retryView);
        }
    }
    
    private void doRegister(List<String> params) {
        String username = params.get(0);
        String password = params.get(1);
        String repassword = params.get(2);
        try {
            if (!password.equals(repassword)) {
                throw new Exception("Passwords don't match.");
            }
            domainController.doRegister(username, password);
            // USer is registered, go to main menu
        } catch (Exception ex) {
            currentView.showErrorMessage(ex.getMessage());
            List<String> actions = Arrays.asList("Retry", "Back");
            List<List<String>> consequences = Utils.asLinkedList(
                Utils.asLinkedList("switch", "register"),
                Utils.asLinkedList("back")
            );

            ActionListView retryView = new ActionListView(actions, consequences);
            retryView.setTitle("Register - Error");
            switchView(retryView);
        }
    }
    
    @Override
    public void viewRequestsAction(List<String> action) {
        // perform copy to avoid modifying original list
        action = new LinkedList<>(action);
        String command = action.remove(0);
        List<String> params = action;
        View view;
        switch (command) {
            case "present":
                // opens a new view on top of the view stack
                view = getViewFromAction(action);
                presentView(view);
                break;
                
            case "switch":
                // opens a new view that substitutes the current one
                view = getViewFromAction(action);
                switchView(view);
                break;
                
            case "login":
                doLogin(params);
                break;
                
            case "register":
                doRegister(params);
                break;
                
            case "back":
                popView();
                break;
                
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
