package not.amazon.echo.network;

/**
 *
 * @author James Colclough
 */
public class JSON_Parser {
    public static String parse(String answer, String searchKey ){
        if (answer.contains(searchKey)) {     
            
           int startIndex = answer.indexOf(searchKey)+searchKey.length(); //Skips past the part of the text we don't need
           int endIndex = startIndex;
           
            while (answer.charAt(endIndex) != '"') {
                endIndex++; //This is done to compute the index at which the answer ends
            } 
            
            answer = answer.substring(startIndex , endIndex);//Only returns the answer String specified by the index
            answer = answer.replace("\\n","").replace("\\t","");//Removes /n and /t parts of string that may be included in a query with multiple answers
            return answer;
        }
        return null;
    }
    
    public static void main(String[] args){
        parse("k","k");
    }
}
