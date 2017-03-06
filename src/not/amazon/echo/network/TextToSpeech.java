package not.amazon.echo.network;

import not.amazon.echo.ErrorHandler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Text to speech conversion using Microsoft Cognitive Services.
 * <p>
 * Go to:
 * http://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 * <p>
 * Inspired from David Wakeling's TextToSpeech code
 *
 * @author Jacques-Antoine Portal 2017
 */

public class TextToSpeech
{



    /*
     * Convert text to speech.
     */
    public static byte[] say(String text) {
        final String method = "POST";
        final String url = "https://speech.platform.bing.com/synthesize";
        final byte[] body
                = ("<speak version='1.0' xml:lang='en-us'>"
                + "<voice xml:lang='" + MSCognitiveServices.LANG + "' "
                + "xml:gender='" + MSCognitiveServices.GENDER + "' "
                + "name='Microsoft Server Speech Text to Speech Voice"
                + " (en-US, ZiraRUS)'>"
                + text
                + "</voice></speak>").getBytes();
        final String[][] headers
                = {{"Content-Type", "application/ssml+xml"}
                , {"Content-Length", String.valueOf(body.length)}
                , {"Authorization", "Bearer " + MSCognitiveServices.getAccessToken()}
                , {"X-Microsoft-OutputFormat", MSCognitiveServices.FORMAT}
        };
        byte[] speech =  HttpConnect.httpConnect(method, url, headers, body);
        return speech;
    }
}
