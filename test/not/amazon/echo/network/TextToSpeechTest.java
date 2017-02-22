/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author jacques-antoine
 */
public class TextToSpeechTest
{

    public TextToSpeechTest()
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
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of say method, of class TextToSpeech.
     */
    @Test
    public void testSay() {
        System.out.println("say");
        String text = "Hi, This is a test for the TextToSpeech method";
        TextToSpeech.say(text);
    }
    
}
