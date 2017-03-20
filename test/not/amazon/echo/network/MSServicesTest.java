/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;

import org.junit.*;

import static org.junit.Assert.fail;

/**
 *
 * @author Jacques-Antoine Portal
 */
public class MSServicesTest
{

    public MSServicesTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //somehow build a mock object
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of renewAccessToken method, of class MSCognitiveServices.
     * assertion that the new token is not the same as before.
     */
    //@Test
    public void testRenewAccessToken() {
        System.out.println("renewAccessToken");
        String oldToken = "";                       //put the old one here somehow
        MSCognitiveServices.renewAccessToken();
        Assert.assertNotEquals("failed to renew AccessToken", oldToken, MSCognitiveServices.getAccessToken());
    }

    /**
     * Test of getAccessToken method, of class MSCognitiveServices.
     * case1: if there is no token, assert it is not null
     * case2: if there is a valid token, assert it is equal to the expected one
     * case3: if there is an invalid token, assert it is not equal to the old one.
     */
    public void testGetAccessToken(){
        System.out.println("getAccessToken");
        String expectedToken;
    }
}
