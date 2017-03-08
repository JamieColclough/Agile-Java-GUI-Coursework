/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jacques-antoine
 */
public class HttpConnectTest {
    
    public HttpConnectTest() {
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
     * Test of httpConnect method, of class HttpConnect.
     */
    //@Test
    public void testHttpConnect() {
        System.out.println("httpConnect");
        String method = "";
        String url = "";
        String[][] headers = null;
        byte[] body = null;
        byte[] expResult = null;
        byte[] result = HttpConnect.httpConnect(method, url, headers, body);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
