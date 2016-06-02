/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

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
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of serialize method, of class Board.
     */
    @Test
    public void testSerialize() {
        System.out.println("serialize");
        Board instance = null;
        String expResult = "";
        String result = instance.serialize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSquareSolution method, of class Board.
     */
    @Test
    public void testGetSquareSolution_int_int() {
        System.out.println("getSquareSolution");
        int i = 0;
        int j = 0;
        Board instance = null;
        int expResult = 0;
        int result = instance.getSquareSolution(i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSquareSolution method, of class Board.
     */
    @Test
    public void testGetSquareSolution_Square() {
        System.out.println("getSquareSolution");
        Square s = null;
        Board instance = null;
        int expResult = 0;
        int result = instance.getSquareSolution(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class Board.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Board instance = null;
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDesigner method, of class Board.
     */
    @Test
    public void testGetDesigner() {
        System.out.println("getDesigner");
        Board instance = null;
        String expResult = "";
        String result = instance.getDesigner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class Board.
     */
    @Test
    public void testValidate() throws Exception {
        System.out.println("validate");
        Board instance = null;
        instance.validate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomBoard method, of class Board.
     */
    @Test
    public void testGenerateRandomBoard() {
        System.out.println("generateRandomBoard");
        Board b = new Board(null, 10);
        b.generateRandomBoard();
    }
    
}