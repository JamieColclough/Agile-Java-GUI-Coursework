package not.amazon.echo.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/*
 * Class containing static methods to send a query to the wolfram alpha server
 * Aspects of code taken from David Wakeling's Computational.java file
 * @author James Colclough 
 * @version 1.3
 */
public class WolframAPI {
    final static String APPID = "QWJHX6-P839WKWR8P";

    /**
     * Method used to encode a string using UTF-8,enabling it to be transfered along the Internet
     *
     * @param s the String which is to be encoded
     * @return the encoded String
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
            System.exit(1);
            return null;
        }
    }

    /**
     * Method for posting a question to the Wolfram Alpha server
     *
     * @param query the question to be asked to the server
     * @return the server's response to the query, in a format ready to be spoken
     */
    private static byte[] serverResponse(String query) throws IOException {
        final String url
                = ("http://api.wolframalpha.com/v1/spoken"
                + "?appid=" + APPID
                + "&i=%22" + urlEncode(query)
        );

        System.out.println(url);
        final String[][] headers = {{"Content-Length", "0"}};

        final byte[] body = new byte[0];
        return HttpConnect.httpConnect("POST", url, headers, body);
    }

    /**
     * Method for taking the server response and converting it into a format that can be used by the echo
     *
     * @param query the Question to ask the server
     * @return the answer, in a string format ready to be used for textToSpeech and other programs
     */
    public static String answer(String query) throws IOException {

        String answer = new String(serverResponse(query));
        return answer;
    }
}
