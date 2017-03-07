package not.amazon.echo.network;

import java.util.Date;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Class containing common attributes for both SpeechToText and
 * TextToSpeech related to Microsoft Cognitive Services.
 * <p>
 * Go to:
 * https://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 * <p>
 * Inspired from David Wakeling's SpeechToText and TextToSpeech code
 *
 * @author Jacques-Antoine Portal 2017
 */

class MSCognitiveServices
{
    public final static String GENDER = "Female";
    public final static String FORMAT = "riff-16khz-16bit-mono-pcm";
    final static String LANG = "en-US";
    static final String appID = "D4D52672-91D7-4C74-8AD8-42B1D98141A5";
    private final static long MAX_DURATION = MILLISECONDS.convert(9, MINUTES);
    private final static String KEY1 = "b47cc7cb3a1d43ce85d798978a6d97b2";
    private static long lastTime;
    private static String token = null;
  
   /*
    *final static String KEY2 = "228990e067f147bf842b70034065efc8";
    */

    public static String getAccessToken() {
        if (token == null || (new Date().getTime() - lastTime) > MAX_DURATION) {
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
                = {{"Ocp-Apim-Subscription-Key", KEY1}
                , {"Content-Length", String.valueOf(body.length)}
        };
        byte[] response = HttpConnect.httpConnect(method, url, headers, body);
        token = new String(response);
        lastTime = new Date().getTime();
    }
}
