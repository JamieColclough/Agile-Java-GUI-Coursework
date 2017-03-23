package not.amazon.echo;

/**
 * @author Adam Mitchell
 */
public class ErrorHandler {
    private static final boolean DEBUG = false;

    public static void log(Throwable throwable) {
        System.out.println("The program encountered a serious error:\n"
                + throwable.getMessage());
        if (DEBUG) {
            throwable.printStackTrace();
        }
    }
}
