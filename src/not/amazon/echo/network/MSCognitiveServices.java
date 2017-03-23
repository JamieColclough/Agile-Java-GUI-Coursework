package not.amazon.echo.network;

import java.io.IOException;
import java.util.Date;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Class containing common attributes for both SpeechToText and
 * TextToSpeech related to Microsoft Cognitive Services.
 * Go to:
 * https://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 * Inspired from David Wakeling's SpeechToText and TextToSpeech code
 *
 * @author Jacques-Antoine Portal 2017
 */

class MSCognitiveServices
{
    public static final String GENDER = "Female";
    public static final String FORMAT = "riff-16khz-16bit-mono-pcm";
    public static final String appID = "D4D52672-91D7-4C74-8AD8-42B1D98141A5";
    public static final String KEY1 = "b47cc7cb3a1d43ce85d798978a6d97b2";
    private static final long MAX_DURATION = MILLISECONDS.convert(9, MINUTES);
    public static final String LANG = "en-US";
    private static long lastTime;
    private static String token = null;
  
   /*
    *final static String KEY2 = "228990e067f147bf842b70034065efc8";
    */


    /**
     * Method that renews the token if necessary, or just returns it.
     * @return a valid Access Token
     */
    static public String getAccessToken() {
        if (token == null || ((new Date().getTime() - lastTime) > MAX_DURATION)) {
            renewAccessToken();
        }
        return token;
    }

    /**
     * This method renews the AccessToken since it expires every 10 minutes
     */
    static public void renewAccessToken() {
        final String method = "POST";
        final String url =
                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        final byte[] body = {};
        final String[][] headers
                = {{"Ocp-Apim-Subscription-Key", KEY1}
                , {"Content-Length", String.valueOf(body.length)}
        };
        byte[] response = new byte[0];
        try {
            response = HttpConnect.httpConnect(method, url, headers, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String newToken = new String(response);
        if (newToken != token){                 //otherwise lastTime should not be changed.
            token = newToken;
            lastTime = new Date().getTime();
        }
    }

    // any method following this is only used for testing
    static public void changeLastTime(long aLastTime){
        lastTime = aLastTime;
    }
    public static String getCurrentAccessToken(){
        return token;
    }


}



