package not.amazon.echo.network;

import java.io.IOException;

/**
 * Text to speech conversion using Microsoft Cognitive Services.
 * Go to:
 * http://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 * Inspired from David Wakeling's TextToSpeech code
 *
 * @author Jacques-Antoine Portal 2017
 */

public class TextToSpeech
{


    /**
     * Method synthesising speech by setting up a http request to Microsoft Cognitive
     * Services servers, sending the request, and returning the sound byte array.
     * the relevant text.
     * @param text  the text that needs to be said
     * @return byte array containing the sound.
     * @throws IOException
     * @Author Jacques-Antoine Portal
     */
    public static byte[] say(String text) throws IOException {
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
        byte[] speech = HttpConnect.httpConnect(method, url, headers, body);
        return speech;
    }
}
