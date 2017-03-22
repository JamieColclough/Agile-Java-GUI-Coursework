package not.amazon.echo.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Make an HTTP connection.
 *
 * David Wakeling, 2017.
 */
class HttpConnect
{
    private final static int TIMEOUT = 5000; /* ms  */
    private final static int BUFFSIZE = 4096; /* 4KB */

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
