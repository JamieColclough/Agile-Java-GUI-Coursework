/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacques-antoine
 */
public class SayTest {
    
    public SayTest() {
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
     * Test of writeData method, of class Say.
     */
    @Test
    public void testWriteData() {
        System.out.println("writeData");
        byte[] buffer = null;
        String name = "";
        Say.writeData(buffer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of say method, of class Say.
     */
    @Test
    public void testSay() {
        System.out.println("say");
        String text = "";
        Say.say(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
