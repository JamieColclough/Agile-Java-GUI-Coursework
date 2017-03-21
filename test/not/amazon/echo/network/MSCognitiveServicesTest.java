/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;

import org.junit.*;

import java.util.Date;

import static org.junit.Assert.fail;

/**
 *
 * @author Jacques-Antoine Portal
 */
public class MSCognitiveServicesTest
{

    public MSCognitiveServicesTest()
    {
    }

    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of renewAccessToken method, of class MSCognitiveServices.
     * assertion that the new token is not the same as before.
     */
    @Test
    public void testRenewAccessToken() {
        System.out.println("renewAccessToken");
        String oldToken = MSCognitiveServices.getCurrentAccessToken();
        MSCognitiveServices.renewAccessToken();
        Assert.assertNotEquals("failed to renew AccessToken", oldToken, MSCognitiveServices.getAccessToken());
    }

    /**
     * Test of getAccessToken method, of class MSCognitiveServices.
     * case1: if there is no token, assert it is not null
     * case2: if there is no token, assert it is equal to the expected one
     * case3: if there is an invalid token, assert it is not equal to the old one.
     */
    @Test
    public void testGetAccessToken(){
        System.out.println("getAccessToken");
        System.out.println("case1");
        String result = MSCognitiveServices.getAccessToken();
        Assert.assertNotNull("token had not been initialised, therefore it should have been renewed", result);
        System.out.println("case2");
        String expected = MSCognitiveServices.getCurrentAccessToken();  //the token should not change.
        result = MSCognitiveServices.getAccessToken();
        Assert.assertEquals("Token should not have changed",expected, result);
        System.out.println("case3");
        //unsuccessful attempt at testing this case, microsoft does not really renew the token
        //with renewAccessToken, it first checks if the there is a valid token, and if so sends
        //it, to verify this, there needs to be a way of waiting 10 minutes.
        //System.out.println(MSCognitiveServices.getCurrentAccessToken());
        //String notExpected = MSCognitiveServices.getCurrentAccessToken();
        //MSCognitiveServices.changeLastTime(0);
        //String result = MSCognitiveServices.getAccessToken();
        //System.out.println(MSCognitiveServices.getCurrentAccessToken());
        //Assert.assertNotEquals(notExpected, result);

    }
}
