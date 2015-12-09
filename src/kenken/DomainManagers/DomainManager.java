/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;

import kenken.Domain.DomainBase;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class DomainManager<T extends DomainBase> {
    public String dbPath;
    public Class managedClass;
    private int queriedChars;
    private int nextId;
    
    public DomainManager() {
        bootUp();
    }
    
    public DomainManager(String dbPath, Class managedClass) {
        this.dbPath = dbPath;
        this.managedClass = managedClass;
        bootUp();
    }
    
    private void bootUp() {
        Path p = Paths.get(dbPath);
        if (!Files.exists(p)) {
            try {
                Files.createFile(Paths.get(dbPath));
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
        getNextId();
    }

    private void getNextId() {
        nextId = 0;
        try {
            FileReader fr = new FileReader(dbPath);
            BufferedReader in = new BufferedReader(fr);
            String line;
            String lastLine = null;
            while ((line = in.readLine()) != null) {
                lastLine = line;
            }
            if (lastLine != null) {
                String stringyId = parseIdFromLine(lastLine);
                nextId = Integer.parseInt(stringyId) + 1;
            }
            in.close();
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public T parseLine(String str) {
        String[] props = str.split(" ");
        try {
            Method m = managedClass.getMethod("deserialize", String[].class);
            T obj = (T) m.invoke(null, (Object) props);
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }
    
    private String parseIdFromLine(String line) {
        String[] data = line.split(" ", 2);
        return data[0];
    }

    public T findOneWith(String key, String value) {
        List<T> results = findWhere(key, value, 1);
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
    
    public List<T> find() {
        return findWhere(null, null, -1);
    }
    
    public List<T> findWhere(String key, String value) {
        return findWhere(key, value, -1);
    }
    
    public List<T> findWhere(String key, String value, int limit) {
        List<T> results = new LinkedList<>();
        queriedChars = 0;
        
        if (limit == 0) {
            return results;
        }
        
        try {
            FileReader fr = new FileReader(dbPath);
            BufferedReader in = new BufferedReader(fr);
            String line = in.readLine();

            while (line != null) {
                T obj = parseLine(line);
                if (key == null || obj.matchesQuery(key, value)) {
                    results.add(obj);
                    if (limit > 0) {
                        --limit;
                        if (limit == 0) {
                            return results;
                        }
                    }
                }
                queriedChars += line.length();
                line = in.readLine();
            }
            return results;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return results;
        }
    }
    
    public T findById(String id) {
        if (id == null) {
            return null;
        }
        
        queriedChars = 0;
        try {
            FileReader fr = new FileReader(dbPath);
            BufferedReader in = new BufferedReader(fr);
            String line = in.readLine();
            while (line != null) {
                String lineId = line.split(" ", 2)[0];
                if (lineId.equals(id)) {
                    T obj = parseLine(line);
                    return obj;
                }
                queriedChars += line.length();
                line = in.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        
        return null;
    }
    
    public void save(T obj) {
        // if obj doesn't have an id assigned, assign it now
        if (obj._id == null) {
            obj._id = String.valueOf(nextId++);
        }
        
        String id = obj._id;
        String serializedObj = obj.serialize();
        Boolean replaced = false;
        
        // this could be optimised if lines always had the same number of bytes
        try {
            Path path = Paths.get(dbPath);
            Path tmpPath = Paths.get(dbPath + ".tmp");
            FileReader fr = new FileReader(dbPath);
            BufferedReader br = new BufferedReader(fr);
            Path createFile = Files.createFile(tmpPath);
            FileWriter fw = new FileWriter(tmpPath.toString());
            BufferedWriter bw = new BufferedWriter(fw);
            String line;
            while ((line = br.readLine()) != null) {
                String lineId = line.split(" ", 2)[0];
                if (!replaced && lineId.equals(id)) {
                    bw.append(serializedObj);
                    replaced = true;
                } else {
                    bw.append(line);
                }
                bw.newLine();
            }
            br.close();
            fr.close();
            if (!replaced) {
                bw.append(serializedObj);
                bw.newLine();
            }
            bw.close();
            fw.close();
            Files.delete(path);
            Files.move(tmpPath, path);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}