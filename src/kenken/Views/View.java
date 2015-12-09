/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Views;

/**
 *
 * @author dasilvacontin
 */
public abstract class View {
    public abstract void run();
    public void showErrorMessage(String msg) {
        System.out.println("/!\\ " + msg);
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    public ViewDelegate delegate;
    public abstract String getTitle();
}