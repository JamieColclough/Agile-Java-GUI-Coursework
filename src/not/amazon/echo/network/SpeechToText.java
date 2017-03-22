package not.amazon.echo.network;

import java.io.IOException;
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

    /**
     * Method to parse the JSON into a text format
     *
     * @param answer    the answer, in a JSON format
     * @param searchKey the key in which the answer is held
     * @return The answer In a string format
     * @throws NoSpeechException
     * @author James Colclough
     */
    public static String parse_JSON(String answer, String searchKey) throws NoSpeechException {
        if (answer.contains(searchKey)) {

            int startIndex = answer.indexOf(searchKey) + searchKey.length(); //Skips past the part of the text we don't need
            int endIndex = startIndex;

            while (answer.charAt(endIndex) != '"') {
                endIndex++; //This is done to compute the index at which the answer ends
            }

            answer = answer.substring(startIndex, endIndex);//Only returns the answer String specified by the index
            return answer;
        }
        throw new NoSpeechException("No Speech Detected.");
    }

    /*
     * Recognize speech.
     */
    public static String recognizeSpeech(byte[] body) throws NoSpeechException, IOException {
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
        if (response == null) {
            throw new NoSpeechException("No Speech Detected.");
        }
        String sResponse = new String(response);
        sResponse = parse_JSON(sResponse, "\"name\":\"");
        return sResponse;
    }
}
