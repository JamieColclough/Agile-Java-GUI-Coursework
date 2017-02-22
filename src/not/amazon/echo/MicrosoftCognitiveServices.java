package not.amazon.echo;

import java.util.Date;
import static java.util.concurrent.TimeUnit.*;

/**
 * Class containing common attributes for both SpeechRecognition and 
 * Say related to Microsoft Cognitive Services.
 *
 * Go to: 
 * https://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 *
 * Inspired from David Wakeling's SpeechToText and TextToSpeech code
 *
 * @author Jacques-Antoine Portal 2017
 */

public class MicrosoftCognitiveServices {
  final static String LANG   = "en-US";
  final static long MAX_DURATION = MILLISECONDS.convert(9, MINUTES);
  static long lastTime;
  static String appID = "P2UKQ6-3UWPGUK5LH";
  static String token;
  final static String KEY1 = "b47cc7cb3a1d43ce85d798978a6d97b2";
  
   /*
    *final static String KEY2 = "228990e067f147bf842b70034065efc8";
    */
  
  /*
     * Renew an access token --- they expire after 10 minutes.
     */
     static void renewAccessToken( String key1 ) {
       final String method = "POST";
       final String url =
         "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
       final byte[] body = {};
       final String[][] headers
         = { { "Ocp-Apim-Subscription-Key", key1                          }
           , { "Content-Length"           , String.valueOf( body.length ) }
           };
       byte[] response = HttpConnect.httpConnect( method, url, headers, body );
       token = new String(response);
       lastTime = new Date().getTime();
     }
}
