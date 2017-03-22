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

import static org.junit.Assert.assertArrayEquals;

/**
 *
 * @author Jacques-Antoine Portal.
 */
public class TextToSpeechTest
{
    byte[] speech;
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
    public void setUp() throws IOException {
        try {
            String fileName = "res/test.wav";
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
     * Test of say method, of class TextToSpeech.
     * asserts expectedSound is equal to testSound
     */
    @Test
    public void testSay() throws IOException {
        System.out.println("say");
        String text = "This is a test.";
        byte[] expected = speech;                                                 //need to put the bytearray here.
        byte[] actual = TextToSpeech.say(text);
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }
    
}
