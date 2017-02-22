/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo;

import org.junit.*;

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
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of speechToText method, of class SpeechRecognition.
     */
    @Test
    public void testSpeechRecognition() {
        System.out.println("speechToText");
        String fileName = "res/eastwood.wav";
        String exResult = "Do I feel lucky? Well, do ya, punk?";
        System.out.println(MicrosoftCognitiveServices.getAccessToken());
        System.out.println(exResult);
        String text = SpeechRecognition.speechRecognition(fileName);
        System.out.println(text);
        assertEquals(text, exResult);
    }
    
}
