/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;
import org.junit.*;
/**
 * Tests for the MSCognitiveServices class
 * @author Jacques-Antoine Portal
 */
public class MSCognitiveServicesTest
{
    String expected;
    String result;
    
    /**
     * Test of renewAccessToken method, of class MSCognitiveServices.
     * assertion that the new token is not the same as before.
     */
    @Test
    public void testRenewAccessToken() {
        System.out.println("renewAccessToken");
        String oldToken = MSCognitiveServices.getCurrentAccessToken();
        MSCognitiveServices.renewAccessToken();
        String newToken = MSCognitiveServices.getAccessToken();
        Assert.assertNotEquals("failed to renew AccessToken", oldToken,newToken);
    }

    /**
     * Test of getAccessToken method, of class MSCognitiveServices.
     * case1: if there is no token, assert it is not null
     * case2: if there is no token, assert it is equal to the expected one
     */
    @Test
    public void testGetAccessToken(){
        System.out.println("getAccessToken");
        System.out.println("case1");
        result = MSCognitiveServices.getAccessToken();
        Assert.assertNotNull("token had not been initialised, therefore it should have been renewed", result);
        
        System.out.println("case2");
        expected = MSCognitiveServices.getCurrentAccessToken();  //the token should not change.
        result = MSCognitiveServices.getAccessToken();
        Assert.assertEquals("Token should not have changed",expected, result);

    }
}
