package not.amazon.echo.network;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the WolframAPI Class
 * As The class depends on a stable Internet connection and the Wolfram Alpha server, This test class
 * can be considered as integration testing
 *
 * @author James Colclough
 * @version 1.1
 */
public class WolframAPITest {

    String query;
    String testResult;
    String expectedResult;

    /**
     * Test of answer method, by asking a random question and asserting it is the same as expected
     */
    @Test
    public void testAnswerCorrect() throws IOException {
        query = "What is 2 + 2?";
        expectedResult = "The answer is 4";
        testResult = WolframAPI.answer(query);
        assertEquals(expectedResult, testResult);
    }

    /**
     * Test of answer method, by asking a question that has no answer and asserting the correct result is returned
     */
    public void testAnswerInvalidQuestion() throws IOException {
        query = "wiehfiauhfiwe4782f";
        expectedResult = "Sorry, I was unable to find an answer to your question";
        testResult = WolframAPI.answer(query);
        assertEquals(expectedResult, testResult);
    }
}
