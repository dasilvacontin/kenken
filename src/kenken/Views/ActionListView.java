/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class ActionListView extends View {
    public List<String> actions;
    public List<List<String>> consequences;
    private String title;
    
    public ActionListView(List<String> actions, List<List<String>> consequences) {
        this.actions = actions;
        this.consequences = consequences;
    }
    
    @Override
    public void run() {
        int choice = requestChoiceFromActionList();
        List<String> consequence = this.consequences.get(choice);
        delegate.viewRequestsAction(consequence);
    }
    
    public int requestChoiceFromActionList() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = -1;
        renderActionList(actions);
        System.out.println("");
        System.out.print("Select an option: ");
        while ((choice < 0) || (actions.size() <= choice)) {
            try {
                String line = br.readLine().trim();
                choice = Integer.parseInt(line);
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            if ((choice < 0) || (actions.size() <= choice)) {
                System.out.print("Not a valid choice, try again: ");
            }
        }
        System.out.println("[x] " + actions.get(choice));
        return choice;
    }
    
    private void renderActionList(List<String> actions) {
        int i = 0;
        for (String action : actions) {
            System.out.println("- " + i + ". " + action);
            ++i;
        }
    }
    
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
