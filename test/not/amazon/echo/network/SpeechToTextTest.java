/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package not.amazon.echo.network;

import org.junit.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Jacques-Antoine Portal
 */
public class SpeechToTextTest
{

    byte[] speech;

    public SpeechToTextTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        try {
            String fileName = "res/eastwood.wav";
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            speech = new byte[(int) file.length()];
            dis.readFully(speech);
            dis.close();
        }catch(Exception e){
            throw(e);
        }
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of speechToText method, of class SpeechToText.
     * asserts that the text returned by speechToText
     * is the expected text.
     * uses eastwood.wav file in res!
     */
    @Test
    public void testSpeechRecognition() throws Exception {
        System.out.println("speechToText");
        String expected = "Do I feel lucky well Do ya punk?";
        String actual = SpeechToText.recognizeSpeech(speech);
        assertEquals(expected, actual);
    }
    
}
