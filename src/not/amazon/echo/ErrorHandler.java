package not.amazon.echo;

/**
 * Created by adammitchell on 23/02/2017.
 */
public class ErrorHandler {
    public static void log(Throwable throwable) {
        System.out.println("The program encountered a serious error:\n"
                + throwable.getMessage());
    }
}
