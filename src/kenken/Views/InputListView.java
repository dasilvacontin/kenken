/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class InputListView extends View {
    final private String title;
    public List<String> inputs;
    public String command;
    
    public InputListView(String title, String command, List<String> inputs) {
        this.title = title;
        this.command = command;
        this.inputs = inputs;
    }
    
    @Override
    public void run() {
        List<String> results = new LinkedList<>();
        results.add(command);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (String input : inputs) {
                System.out.print(input + ": ");
                String line = br.readLine().trim();
                results.add(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        delegate.viewRequestsAction(results);
    }

    @Override
    public String getTitle() {
        return title;
    }
}
