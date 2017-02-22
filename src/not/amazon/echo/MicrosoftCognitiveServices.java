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
  static String appID = "D4D52672-91D7-4C74-8AD8-42B1D98141A5";
  static String token = null;
  final static String KEY1 = "b47cc7cb3a1d43ce85d798978a6d97b2";
  
   /*
    *final static String KEY2 = "228990e067f147bf842b70034065efc8";
    */

  public static String getAccessToken() {
    if(token == null || (new Date().getTime() - lastTime) > MAX_DURATION) {
      renewAccessToken();
    }
    return token;
  }

  /*
     * Renew an access token --- they expire after 10 minutes.
     */
     static void renewAccessToken() {
       final String method = "POST";
       final String url =
         "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
       final byte[] body = {};
       final String[][] headers
         = { { "Ocp-Apim-Subscription-Key", KEY1                          }
           , { "Content-Length"           , String.valueOf( body.length ) }
           };
       byte[] response = HttpConnect.httpConnect( method, url, headers, body );
       token = new String(response);
       lastTime = new Date().getTime();
     }
}