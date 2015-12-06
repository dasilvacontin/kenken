/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;

import kenken.Domain.DomainBase;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dasilvacontin
 * @param <T>
 */
public class DomainManager {
    public String dbPath;
    public int keyCount;
    public Class managedClass;
    
    public DomainManager() {
        managedClass = DomainBase.class;
    }
    
    int indexForKey(String key) {
        return -1;
    }
    
    public DomainBase findOneWith(String key, String value) throws IOException, InstantiationException, IllegalAccessException{
        int targetKeyIndex = indexForKey(key);

        FileReader fr = new FileReader(dbPath);
        BufferedReader in = new BufferedReader(fr);
        String line = in.readLine();
        
        List<String> data = new LinkedList<>();
        int currentKeyIndex = 0;
        while (line != null) {
            // skip blank lines to allow better formatting
            if (line.equals("\n")) {
                line = in.readLine();
                continue;
            }
            
            // add line to prop list
            data.add(line);
            ++currentKeyIndex;
            
            // check for end of object description
            if (currentKeyIndex >= keyCount) {
                String valueForTargetKey = data.get(targetKeyIndex);
                if (valueForTargetKey.equals(value)) {
                    // we have a winner
                    System.out.println("Found!");
                    System.out.println(data);
                    DomainBase one = null;
                    try {
                        one = (DomainBase) managedClass.getConstructor(List.class).newInstance(data);
                    } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(DomainManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return one;
                } else {
                    // reset variables for next object
                    currentKeyIndex = 0;
                    data.clear();
                }
            }
            line = in.readLine();
        }
        return null;
    }
}
