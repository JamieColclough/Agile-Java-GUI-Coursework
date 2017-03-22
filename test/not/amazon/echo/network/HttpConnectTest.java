/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

/**
 *
 * @author jacques-antoine
 */
public class HttpConnectTest {
    String method;
    String url;
    byte[] body;
    String[][] headers;
    public HttpConnectTest() {
    }

    @Test
    public void testHttpConnect1() throws Exception {

    }

    @Before
    public void setUp() {
        method = "POST";
        url = "https://speech.platform.bing.com/synthesize";
        body
                = ("<speak version='1.0' xml:lang='en-us'>"
                + "<voice xml:lang='" + MSCognitiveServices.LANG + "' "
                + "xml:gender='" + MSCognitiveServices.GENDER + "' "
                + "name='Microsoft Server Speech Text to Speech Voice"
                + " (en-US, ZiraRUS)'>"
                + "hey"
                + "</voice></speak>").getBytes();
        headers
                = new String[][]{{"Content-Type", "application/ssml+xml"}
                , {"Content-Length", String.valueOf(body.length)}
                , {"Authorization", "Bearer " + MSCognitiveServices.getAccessToken()}
                , {"X-Microsoft-OutputFormat", MSCognitiveServices.FORMAT}
        };
    }
    
    @After
    public void tearDown() {
        //there is only one test really....
    }

    /**
     * Test of httpConnect method, of class HttpConnect.
     * This test is a copy of the say method using httpConnect, and then
     * it compares the result of httpConnect to the result of say().
     */
    @Test
    public void testHttpConnect() throws IOException {
        System.out.println("httpConnect");
        byte[] result = HttpConnect.httpConnect(method, url, headers, body);
        byte[] expResult = TextToSpeech.say("hey");
        assertArrayEquals("The result from using httpConnect is not correct.", expResult, result);
    }
    
}
