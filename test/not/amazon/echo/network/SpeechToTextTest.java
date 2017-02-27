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
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of speechToText method, of class SpeechToText.
     */
    @Test
    public void testSpeechRecognition() throws IOException {
        //TODO needs commenting

        System.out.println("speechToText");
        String fileName = "res/eastwood.wav";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        byte[] buffer = new byte[(int) file.length()];
        dis.readFully(buffer);
        dis.close();
        String exResult = "Do I feel lucky well do you punk?";
        String text = SpeechToText.recognizeSpeech(buffer);
        assertEquals(text, exResult);
    }
    
}
