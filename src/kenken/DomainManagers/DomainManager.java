/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;

import kenken.Domain.DomainBase;
import java.io.*;
import java.lang.reflect.Method;

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
        try {
            FileReader fr = new FileReader(dbPath);
            BufferedReader in = new BufferedReader(fr);
            String line = in.readLine();

            while (line != null) {
                DomainBase obj = parseLine(line);
                if (obj.matchesQuery(key, value)) {
                    return obj;
                }
                line = in.readLine();
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }
}