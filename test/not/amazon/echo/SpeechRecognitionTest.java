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
 * @author Jacques-Antoine Portal
 */
public class SpeechRecognitionTest {
    
    public SpeechRecognitionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        SpeechRecognition aSR = new SpeechRecognition();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of renewAccessToken method, of class SpeechRecognition.
     */
    @Test
    public void testRenewAccessToken() {
        System.out.println("renewAccessToken");
        String key1 = "";
        SpeechRecognition.renewAccessToken(key1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readData method, of class SpeechRecognition.
     */
    @Test
    public void testReadData() {
        System.out.println("readData");
        String name = "";
        byte[] expResult = null;
        byte[] result = SpeechRecognition.readData(name);
    }

    /**
     * Test of speechToText method, of class SpeechRecognition.
     */
    @Test
    public void testSpeechRecognition() {
        System.out.println("speechToText");
        String fileName = "eastwood.wav";
        String exResult = "Do I feel luucky? Well, do ya, punk?";
        System.out.println(exResult);
        String text = SpeechRecognition.speechRecognition(fileName);
        System.out.println(text);
        assertEquals(text, exResult);
    }
    
}
