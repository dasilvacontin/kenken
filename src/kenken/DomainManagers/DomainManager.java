/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;

import kenken.Domain.DomainBase;
import java.io.*;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class DomainManager {
    public String dbPath;
    public Class managedClass;

    public DomainBase parseLine(String str) {
        try {
            Method m = managedClass.getMethod("deserialize", String.class);
            return (DomainBase) m.invoke(null, str);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public DomainBase findOneWith(String key, String value) {
        List<DomainBase> results = findWhere(key, value, 1);
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
    
    public List<DomainBase> find() {
        return findWhere(null, null, -1);
    }
    
    public List<DomainBase> findWhere(String key, String value) {
        return findWhere(key, value, -1);
    }
    
    public List<DomainBase> findWhere(String key, String value, int limit) {
        List<DomainBase> results = new LinkedList<>();
        
        if (limit == 0) {
            return results;
        }
        
        try {
            FileReader fr = new FileReader(dbPath);
            BufferedReader in = new BufferedReader(fr);
            String line = in.readLine();

            while (line != null) {
                DomainBase obj = parseLine(line);
                if (key == null || obj.matchesQuery(key, value)) {
                    results.add(obj);
                    if (limit > 0) {
                        --limit;
                        if (limit == 0) {
                            return results;
                        }
                    }
                }
                line = in.readLine();
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return results;
        }
    }
}