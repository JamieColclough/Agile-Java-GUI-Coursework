package not.amazon.echo.network;

/**
 * Class containing the method to make an http connection
 * to Microsoft Cognitive Services.
 * Go to:
 * https://www.microsoft.com/cognitive-services/en-us/speech-api
 * for more information.
 * From David Wakeling's SpeechToText and TextToSpeech code
 */

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

class HttpConnect
{
    private final static int TIMEOUT = 5000; /* ms  */
    private final static int BUFFSIZE = 4096; /* 4KB */


    /**
     * the method used to connect to Microsoft Cognitive Services
     * @param method  in our case POST
     * @param url     URL to Microsoft Cognitive Services
     * @param headers relevant headers for the request
     * @param body    either a token to be renewed, speech to be translated to text, or text to be translated to speech.
     * @return
     * @throws IOException
     * @author David Wakeling
     */
    public static byte[] httpConnect
            (String method
                    , String url
                    , String[][] headers
                    , byte[] body
            ) throws IOException {

  /*
   * Setup connection.
   */
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(TIMEOUT);
        for (String[] header : headers) {
            conn.setRequestProperty(header[0], header[1]);
        }
        conn.connect();

  /*
   * Send data.
   */
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(body);
        dos.flush();
        dos.close();

  /*
   * Receive data.
   */
        DataInputStream dis = new DataInputStream(conn.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buffer = new byte[BUFFSIZE];
        for (; ; ) {
            int n = dis.read(buffer);
            if (n > 0) {
                bos.write(buffer, 0, n);
            } else {
                break;
            }
        }

        byte response[] = bos.toByteArray();
        dis.close();

  /*
   * Teardown connection.
   */
        conn.disconnect();

        return response;
    }
}
