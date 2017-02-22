package not.amazon.echo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.util.Date;

/**
 * Text to speech conversion using Microsoft Cognitive Services.
 *
 * Go to: 
 * http://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 * 
 * Inspired from David Wakeling's TextToSpeech code
 *
 * @author Jacques-Antoine Portal 2017
 */

public class Say {
  final static String GENDER = "Female";
  final static String OUTPUT = "output.wav";
  final static String FORMAT = "riff-16khz-16bit-mono-pcm";

   /*
    * Synthesize speech.
    */
   static byte[] synthesizeSpeech( String text
                                 , String lang,  String gender
                                 , String format ) {
     final String method = "POST";
     final String url = "https://speech.platform.bing.com/synthesize";
     final byte[] body
       = ( "<speak version='1.0' xml:lang='en-us'>"
         + "<voice xml:lang='" + lang   + "' "
         + "xml:gender='"      + gender + "' "
         + "name='Microsoft Server Speech Text to Speech Voice"
         + " (en-US, ZiraRUS)'>"
         + text
         + "</voice></speak>" ).getBytes();
     final String[][] headers
       = { { "Content-Type"             , "application/ssml+xml"        }
         , { "Content-Length"           , String.valueOf( body.length ) }
         , { "Authorization"            , "Bearer " + MicrosoftCognitiveServices.getAccessToken() }
         , { "X-Microsoft-OutputFormat" , format                        }
         };
     byte[] response = HttpConnect.httpConnect( method, url, headers, body );
     return response;
   }


   /*
    * Write data to file.
    */
   static void writeData( byte[] buffer, String name ) {
     try {
       File             file = new File( name );
       FileOutputStream fos  = new FileOutputStream( file );
       DataOutputStream dos  = new DataOutputStream( fos );
       dos.write( buffer );
       dos.flush();
       dos.close();
     } catch ( Exception ex ) {
       System.out.println( ex ); System.exit( 1 ); return;
     }
   }


   /*
    * Convert text to speech.
    */
   public static void say( String text ) {
     final byte[] speech = synthesizeSpeech( text, MicrosoftCognitiveServices.LANG, GENDER, FORMAT );
     writeData( speech, OUTPUT );
   }
 }
