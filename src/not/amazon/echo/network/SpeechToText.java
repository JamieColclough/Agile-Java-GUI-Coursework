package not.amazon.echo.network;

import not.amazon.echo.ErrorHandler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

/**
 * Speech to text conversion using Microsoft Cognitive Services.
 * <p>
 * Go to:
 * https://www.microsoft.com/cognitive-services/en-us/speech-api/documentation/overview
 * for more information.
 * <p>
 * Inspired from David Wakeling's SpeechRecognition code
 *
 * @author Jacques-Antoine Portal 2017
 */

public class SpeechToText {
//all attributes are inherited from Microsoft Cognitive Services.

    /*
     * Recognize speech.
     */
    public static String recognizeSpeech(byte[] body) {
        final String method = "POST";
        final String url
                = ("https://speech.platform.bing.com/recognize"
                + "?" + "version" + "=" + "3.0"
                + "&" + "format" + "=" + "json"
                + "&" + "device.os" + "=" + "wp7"
                + "&" + "scenarios" + "=" + "smd"
                + "&" + "locale" + "=" + MSCognitiveServices.LANG
                + "&" + "appid" + "=" + MSCognitiveServices.appID
                + "&" + "instanceid" + "=" + UUID.randomUUID().toString()
                + "&" + "requestid" + "=" + UUID.randomUUID().toString()
                + "&" + "result.profanity" + "=" + "0"
        );
        final String[][] headers
                = {{"Content-Type", "audio/wav; samplerate=16000"}
                , {"Content-Length", String.valueOf(body.length)}
                , {"Authorization", "Bearer " + MSCognitiveServices.getAccessToken()}
        };
        byte[] response = HttpConnect.httpConnect(method, url, headers, body);
        //response includes all the JSON context, for this application we only need the actual thing that was said,
        // the following part of this method will find, and return the useful bit of response.
        String sResponse = new String(response);
        int i;
        if (sResponse.contains("\"name\":\"")) {
            i = sResponse.indexOf("\"name\":\"") + 9;       //+9 to skip all the characters in the string
            while (sResponse.charAt(i) != '"') {
                i = i + 1;
            }
        } else {
            return null;
        }
        return sResponse.substring(sResponse.indexOf("\"name\":\"") + 8, i);
    }

    /*
     * Read data from file.
     */
    @Deprecated
    private static byte[] readData(String name) {
        try {
            File file = new File(name);
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            byte[] buffer = new byte[(int) file.length()];
            dis.readFully(buffer);
            dis.close();
            return buffer;
        } catch (Exception ex) {
            ErrorHandler.log(ex);
            System.exit(1);
            return null;
        }
    }

    /*
     * Convert speech to text.
     */
    @Deprecated
    public static String speechRecognition(String fileName) {
        final byte[] speech = readData(fileName);  //reads a .wav (sound) file
        return recognizeSpeech(speech);            //converts the speech into txt prints it
    }
}
