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
import static org.junit.Assert.fail;

/**
 * Tests for the SpeechToText class
 * @author Jacques-Antoine Portal and James Colclough
 */
public class SpeechToTextTest
{

    byte[] speech;
    String testJSON = "\"irrelevantData\" : \"ww\" \"testData\" : \"tested\"";
    String expected;
    String actual;
    
    @Before
    public void setUp() throws IOException {
            String fileName = "res/test.wav";
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            speech = new byte[(int) file.length()];
            dis.readFully(speech);
            dis.close();
    }

    /**
     * Test of speechToText method, of class SpeechToText.
     * asserts that the text returned by speechToText
     * is the expected text.
     * uses test.wav file in res!
     */
    @Test
    public void testSpeechRecognition() throws Exception {
        System.out.println("speechToText");
        expected = "This is a test.";
        actual = SpeechToText.recognizeSpeech(speech);
        assertEquals(expected, actual);
    }
    
    /**
     * Test of the parse_JSON method
     * asserts that the correct segment of JSON is parsed and returned
     */
    @Test
    public void testParse_JSON(){
        try{
        actual = SpeechToText.parse_JSON(testJSON,"\"testData\" : \"");
        }catch(NoSpeechException e){fail();}
        expected = "tested";
        assertEquals(expected,actual);
    }
    
}
