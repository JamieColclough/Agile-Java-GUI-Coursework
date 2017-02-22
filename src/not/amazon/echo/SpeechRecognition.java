package not.amazon.echo;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.UUID;
import java.util.Date;

/**
 * Speech to text conversion using Microsoft Cognitive Services.
 *
 * Go to: 
 * https://www.microsoft.com/cognitive-services/en-us/speech-api/documentation/overview
 * for more information.
 *
 * Inspired from David Wakeling's SpeechToText code
 *
 * @author Jacques-Antoine Portal 2017
 */

public class SpeechRecognition {
//all attributes are enherited from Microsoft Cognitive Services.

     /*
      * Recognize speech.
      */
     static String recognizeSpeech( byte[] body ) {
       final String method = "POST";
       final String url
         = ( "https://speech.platform.bing.com/recognize"
           + "?" + "version"    + "=" + "3.0"
           + "&" + "format"     + "=" + "json"
           + "&" + "device.os"  + "=" + "wp7"
           + "&" + "scenarios"  + "=" + "smd"
           + "&" + "locale"     + "=" + MicrosoftCognitiveServices.LANG
           + "&" + "appid"      + "=" + MicrosoftCognitiveServices.appID
           + "&" + "instanceid" + "=" + UUID.randomUUID().toString()
           + "&" + "requestid"  + "=" + UUID.randomUUID().toString()
           + "&" + "result.profanity" + "=" + "0"
           );
       final String[][] headers
         = { { "Content-Type"   , "audio/wav; samplerate=16000"  }
           , { "Content-Length" , String.valueOf( body.length )  }
           , { "Authorization"  , "Bearer " + MicrosoftCognitiveServices.getAccessToken()              }
           };
       byte[] response = HttpConnect.httpConnect( method, url, headers, body );
       return new String( response );
     }

     /*
      * Read data from file.
      */
     static byte[] readData( String name ) {
       try {
         File            file = new File( name );
         FileInputStream fis  = new FileInputStream( file );
         DataInputStream dis  = new DataInputStream( fis );
         byte[] buffer = new byte[ (int) file.length() ];
         dis.readFully( buffer );
         dis.close();
         return buffer;
       } catch ( Exception ex ) {
         System.out.println( ex ); System.exit( 1 ); return null;
       }
     }

     /*
      * Convert speech to text.
      */
     public static String speechRecognition( String fileName ) {
       final byte[] speech = readData( fileName );                          //reads a .wav (sound) file
       System.out.println("read the file");
       final String text   = recognizeSpeech( speech );     //converts the speech into txt
       System.out.println("this is the text: ");
       return text;                                                   //prints it
     }
   }
