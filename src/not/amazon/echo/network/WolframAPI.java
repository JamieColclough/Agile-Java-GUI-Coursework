package not.amazon.echo.network;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/*
 * Class containing static methods to send a query to the wolfram alpha server
 * Aspects of code taken from David Wakeling's Computational.java file
 * @author James Colclough 
 * @version 1.2
 */
public class WolframAPI {
  final static String APPID   = "QWJHX6-P839WKWR8P";
  
    /**
     * Method used to encode a string using UTF-8,enabling it to be transfered along the Internet
     * @param s the String which is to be encoded
     * @return the encoded String
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        } catch ( UnsupportedEncodingException ex ) {
            System.out.println(ex);
            System.exit( 1 ); return null;
        }
    } 
  
    /**
     * Method for posting a question to the Wolfram Alpha server
     * @param query the question to be asked to the server
     * @return the server's response to the query, in JSON format
     */
    private static byte[] serverResponse(String query) {
        final String url    
          = ( "http://api.wolframalpha.com/v2/query"
            + "?appid=" + APPID
            + "&input=" + urlEncode(query)
            + "&output=JSON"
            + "&includepodid=Result" //Narrows down parameters so only the json result is returned
            );

        final String[][] headers = { {"Content-Length", "0"} };

        final byte[] body = new byte[0];
        return HttpConnect.httpConnect("POST", url, headers, body); 
    }
    
    /**
     * Method for taking the server response and converting it into a format that can be used by the echo
     * @param query the Question to ask the server
     * @return the answer, in a string format ready to be used for textToSpeech and other programs
     */
    public static String answer(String query){
      
        String answer = new String(serverResponse(query));     

        answer = JSON_Parser.parse(answer, "\"plaintext\" : \"");
         
        if (answer == null){
            return "Sorry, I was unable to find an answer to your question"; //In the case that no answer was returned from the server
        }
        else{ return answer;}
    }
}
