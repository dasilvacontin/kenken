/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import kenken.Domain.DomainBase;
import kenken.Domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dasilvacontin
 */
public class DomainManagerTest {
    
    private DomainManager<User> manager;
    private final Path dbPath;
    
    public DomainManagerTest() {
        dbPath = Paths.get("./User-test.db");
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        // set up test db file
        Files.createFile(dbPath);
        FileWriter fw = new FileWriter(dbPath.toString());
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("0 dasilvacontin 123");
        bw.newLine();
        bw.write("1 flurry 123");
        bw.newLine();
        bw.close();
        
        manager = new DomainManager<>(dbPath.toString(), User.class);
    }
    
    @After
    public void tearDown() {
        try {
            Files.delete(dbPath);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Test of parseLine method, of class DomainManager.
     */
    @Test
    public void testParseLine() {
        System.out.println("* DomainManager parseLine()");
        String serializedObject = "0 flurry 123";
        User u = manager.parseLine(serializedObject);
        assertTrue(u._id.equals("0"));
        assertTrue(u.getUsername().equals("flurry"));
        assertTrue(u.getPassword().equals("123"));
    }

    /**
     * Test of findOneWith method, of class DomainManager.
     */
    @Test
    public void testFindOneWith() {
        System.out.println("* DomainManager findOneWith()");
        User u = manager.findOneWith("username", "flurry");
        assertTrue(u._id.equals("1"));
        assertTrue(u.getUsername().equals("flurry"));
        assertTrue(u.getPassword().equals("123"));
    }

    /**
     * Test of find method, of class DomainManager.
     */
    @Test
    public void testFind() {
        System.out.println("* DomainManager find()");
        List<User> result = manager.find();
        assertTrue(result.size() == 2);
        
        // dasilvacontin user
        User u = result.get(0);
        assertTrue(u._id.equals("0"));
        assertTrue(u.getUsername().equals("dasilvacontin"));
        assertTrue(u.getPassword().equals("123"));
        
        // flurry user
        u = result.get(1);
        assertTrue(u._id.equals("1"));
        assertTrue(u.getUsername().equals("flurry"));
        assertTrue(u.getPassword().equals("123"));
    }

    /**
     * Test of findWhere method, of class DomainManager.
     */
    @Test
    public void testFindWhere_String_String() {
        System.out.println("* DomainManager findWhere_String_String()");
        User u = new User("dasilvacontin", "456");
        manager.save(u);
        List<User> result = manager.findWhere("username", "dasilvacontin");
        
        assertTrue(result.size() == 2);
        
        // dasilvacontin user
        u = result.get(0);
        assertTrue(u._id.equals("0"));
        assertTrue(u.getUsername().equals("dasilvacontin"));
        assertTrue(u.getPassword().equals("123"));
        
        // dasilvacontin (2) user
        u = result.get(1);
        assertTrue(u._id.equals("2"));
        assertTrue(u.getUsername().equals("dasilvacontin"));
        assertTrue(u.getPassword().equals("456"));
    }

    /**
     * Test of findWhere method, of class DomainManager.
     */
    @Test
    public void testFindWhere_3args() {
        System.out.println("* DomainManager findWhere(key, value, limit)");
        User u = new User("dasilvacontin", "456");
        manager.save(u);
        u = new User("dasilvacontin", "789");
        
        List result = manager.findWhere("username", "dasilvacontin", 1);
        assertTrue(result.size() == 1);
        
        result = manager.findWhere("username", "dasilvacontin", 2);
        assertTrue(result.size() == 2);
        
        result = manager.findWhere("username", "dasilvacontin", 0);
        assertTrue(result.size() == 0);
        
        result = manager.findWhere("username", "w0l0l0", 1);
        assertTrue(result.size() == 0);
    }

    /**
     * Test of findById method, of class DomainManager.
     */
    @Test
    public void testFindById() {
        System.out.println("* DomainManager findById()");
        
        User u = manager.findById("1");
        assertTrue(u.getUsername().equals("flurry"));
    }

    /**
     * Test of save method, of class DomainManager.
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testSave() throws FileNotFoundException, IOException {
        System.out.println("* DomainManager save()");
        String username = "w0l0l0";
        User u = manager.findOneWith("username", username);
        assertTrue(u == null);
        
        u = new User(username, "cobra");
        manager.save(u);
        User stored = manager.findOneWith("username", username);
        assertTrue(u._id.equals(stored._id));
        
        FileReader fr = new FileReader(dbPath.toString());
        BufferedReader br = new BufferedReader(fr);
        String line;
        String su = u.serialize();
        Boolean foundInFile = false;
        while ((line = br.readLine()) != null) {
            if (line.equals(su)) {
                foundInFile = true;
                break;
            }
        }
        assertTrue(foundInFile);
    }
    
}
